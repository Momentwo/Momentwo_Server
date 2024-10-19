package cord.eoeo.momentwo.image.application.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

public interface ImageManager {
    String getPresignedUrl(String imageExtension, String path);
    CompletableFuture<Void> imageDelete(String filename);
    CompletableFuture<URL> imageFileSearch(String filename);
    CompletableFuture<MultipartFile> makeMultipartFileByS3Image(String filename);
}