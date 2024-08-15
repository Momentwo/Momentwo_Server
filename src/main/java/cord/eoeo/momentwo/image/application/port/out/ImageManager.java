package cord.eoeo.momentwo.image.application.port.out;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public interface ImageManager {
    CompletableFuture<String> imageUpload(MultipartFile image, String path);
    void imageDelete(String Path, String imageUrl);
    CompletableFuture<ResponseEntity<Resource>> imageDownload(Path path);
    CompletableFuture<ResponseEntity<Resource>> imageView(String filename);
}