package cord.eoeo.momentwo.image.adapter.out;

import cord.eoeo.momentwo.image.advice.exception.NotFoundImageException;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import cord.eoeo.momentwo.image.path.ImagePath;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ImageManagerImpl implements ImageManager {
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

                // 서버 이미지 저장 경로
                Path uploadPath = Paths.get(path);

                // 경로가 없다면 경로 생성
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // 해당 이미지를 저장 경로에 복사
                try (InputStream inputStream = image.getInputStream()) {
                    Path filePath = uploadPath.resolve(newFilename);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                return newFilename;
            } catch (Exception e) {
                throw new NotFoundImageException();
            }
        });
    }

    @Override
    public void imageDelete(String path, String imageUrl) {
        if(imageUrl != null && !imageUrl.isEmpty()) {
            deleteImage(Paths.get(path, imageUrl));
        }
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<Resource>> imageDownload(Path path) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Resource resource = new UrlResource(path.toUri());

                // 파일이 존재하지 않으면 해당 리턴
                if (!resource.exists()) {
                    return ResponseEntity.notFound().build();
                }

                // 헤더에 다운로드로 유도하게 전송
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } catch (Exception e) {
                return ResponseEntity.status(500).build();
            }
        });
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<Resource>> imageView(String filename) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(ImagePath.SERVER_IMAGE_PATH.getPath()).resolve(filename).normalize();

                Resource resource = new UrlResource(path.toUri());
                if(!resource.exists()) {
                    return ResponseEntity.notFound().build();
                }

                String contentType = "image/jpeg"; // 기본 이미지 확장자 설정
                if(filename.endsWith(".png")) {
                    contentType = "image/png";
                } else if (filename.endsWith(".gif")) {
                    contentType = "image/gif";
                }

                // 이미지 반환
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);

            } catch (Exception e) {
                return ResponseEntity.status(500).build();
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
}
