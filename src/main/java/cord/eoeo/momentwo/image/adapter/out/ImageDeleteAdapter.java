package cord.eoeo.momentwo.image.adapter.out;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.application.port.out.ImageDeletePort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ImageDeleteAdapter implements ImageDeletePort {
    private final S3Manager s3Manager;
    private final S3Client s3Client;

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
}
