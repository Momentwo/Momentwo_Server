package cord.eoeo.momentwo.image.adapter.out;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.application.port.out.MakeImagePresignedUrlPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.time.Duration;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MakeImagePresignedUrlAdapter implements MakeImagePresignedUrlPort {
    private final S3Manager s3Manager;
    private final S3Presigner s3Presigner;
    private final int EXPIRATION_TIME = 10;

    @Override
    public String makeImagePresignedUrl(String imageExtension, String path) {
        // UUID 를 통한 고유한 이름 생성
        String newFilename = UUID.randomUUID() + "." + imageExtension;
        // s3 이미지 저장 경로
        String key = path + newFilename;
        // s3 요청 정보
        PutObjectRequest objectRequest = uploadFile(key, imageExtension);

        PresignedPutObjectRequest presignedPutObjectRequest = s3Presigner.presignPutObject(
                presignedRequest ->
                        presignedRequest.putObjectRequest(objectRequest)
                                .signatureDuration(Duration.ofMinutes(EXPIRATION_TIME))
        );

        return presignedPutObjectRequest.url().toString();
    }

    private PutObjectRequest uploadFile(String key, String type) {
        String imageType = "image/" + type; // 확장자 MIME 타입으로 만들기

        return PutObjectRequest.builder()
                .bucket(s3Manager.getBucketName())
                .key(key)
                .contentType(imageType) // MIME 타입으로 들어가야함
                .build();
    }
}
