package cord.eoeo.momentwo.image.adapter.out;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.advice.exception.NotFoundImageException;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ImageManagerImpl implements ImageManager {
    private final S3Client s3Client;
    private final S3Manager s3Manager;

    @Override
    @Async
    public CompletableFuture<String> imageUpload(MultipartFile image, String path) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String originalFilename = image.getOriginalFilename();
                String fileExtension = Objects.requireNonNull(originalFilename)
                        .substring(originalFilename.lastIndexOf("."));

                // UUID 를 통한 고유한 이름 생성
                String newFilename = UUID.randomUUID() + fileExtension;

                // s3 이미지 저장 경로
                String key = path + newFilename;

                // 해당 이미지를 s3 저장소에 저장
                try (InputStream inputStream = image.getInputStream()) {
                    uploadFile(key, inputStream, image.getSize(), image.getContentType());
                }

                return newFilename;
            } catch (Exception e) {
                throw new NotFoundImageException();
            }
        });
    }

    @Override
    @Async
    public CompletableFuture<Void> imageDelete(String filename) {
        return CompletableFuture.runAsync(() -> {
            // S3에서 삭제 요청
            DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                    .bucket(s3Manager.getBucketName())
                    .key(filename) // 삭제할 파일의 키
                    .build();

            // 객체 삭제
            s3Client.deleteObject(deleteRequest);
        });
    }

    @Override
    @Async
    public CompletableFuture<URL> imageFileSearch(String filename) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 객체를 가져오고 URL을 생성
                return s3Client.utilities().getUrl(b -> b.bucket(s3Manager.getBucketName()).key(filename));

            } catch (Exception e) {
                throw new NotFoundImageException();
            }
        });
    }

    @Override
    @Async
    public CompletableFuture<MultipartFile> makeMultipartFileByS3Image(String filename) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // S3에서 파일 가져오기
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(s3Manager.getBucketName())
                        .key(filename)
                        .build();

                InputStream inputStream = s3Client.getObject(getObjectRequest);

                // InputStream을 byte 배열로 변환
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, length);
                }

                byte[] fileContent = byteArrayOutputStream.toByteArray();

                // 파일 이름과 확장자 지정
                String contentType = Files.probeContentType(Path.of(filename)); // 파일 타입 결정

                // MockMultipartFile로 변환
                return new CustomMultipartFile(fileContent, filename, contentType);
            } catch (Exception e) {
                throw new NotFoundImageException();
            }
        });
    }

    private void deleteImage(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new NotFoundImageException();
        }
    }

    private void uploadFile(String key, InputStream inputStream, long len, String type) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Manager.getBucketName())
                .key(key)
                .contentLength(len)
                .contentType(type)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, len));
    }
}
