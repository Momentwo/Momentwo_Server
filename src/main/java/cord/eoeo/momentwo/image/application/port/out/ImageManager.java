package cord.eoeo.momentwo.image.application.port.out;

import cord.eoeo.momentwo.image.adapter.dto.ImageDownLoadResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public interface ImageManager {
    CompletableFuture<String> imageUpload(MultipartFile image, String path);
    void imageDelete(String Path, String imageUrl);
    CompletableFuture<ImageDownLoadResponseDto> imageDownload(Path path);
    CompletableFuture<Void> profileFileSearch(String filename);
    CompletableFuture<Void> imageFileSearch(String filename);
}