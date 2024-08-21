package cord.eoeo.momentwo.image.adapter.out;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.image.adapter.dto.ImageDownLoadResponseDto;
import cord.eoeo.momentwo.image.advice.exception.ImageDownloadFailException;
import cord.eoeo.momentwo.image.advice.exception.NotFoundFileImageException;
import cord.eoeo.momentwo.image.advice.exception.NotFoundImageException;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import cord.eoeo.momentwo.image.path.ImagePath;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
    private final AlbumManager albumManager;

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
    public CompletableFuture<ImageDownLoadResponseDto> imageDownload(Path path) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Resource resource = new UrlResource(path.toUri());

                // 파일이 존재하지 않으면 해당 리턴
                if (!resource.exists()) {
                    throw new NotFoundFileImageException();
                }

                return new ImageDownLoadResponseDto().toDo(resource.getFilename());
            } catch (Exception e) {
                throw new ImageDownloadFailException();
            }
        });
    }

    @Override
    @Async
    public CompletableFuture<Resource> profileFileSearch(String filename) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(ImagePath.SERVER_PROFILE_PATH.getPath()).resolve(filename).normalize();

                Resource resource = new UrlResource(path.toUri());

                // 이미지가 저장되어 있지 않다면 기본 이미지로 반환
                if(!resource.exists()) {
                    return new UrlResource(
                            Paths.get(ImagePath.SERVER_PROFILE_PATH.getPath())
                                    .resolve(albumManager.getBaseImage())
                                    .normalize().toUri()
                    );
                }

                return resource;
            } catch (Exception e) {
                throw new NotFoundImageException();
            }
        });
    }

    @Override
    @Async
    public CompletableFuture<Resource> imageFileSearch(String filename) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(ImagePath.SERVER_IMAGE_PATH.getPath()).resolve(filename).normalize();

                return new UrlResource(path.toUri());
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
}
