package cord.eoeo.momentwo.image.adapter.out;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.application.port.out.ImageListDeletePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ImageListDeleteAdapter implements ImageListDeletePort {
    private final S3Manager s3Manager;
    private final S3Client s3Client;

    @Override
    public void imageListDelete(List<String> keys) {
        // ObjectIdentifier 리스트 생성
        List<ObjectIdentifier> objectIdentifiers = keys.stream()
                .map(key -> ObjectIdentifier.builder().key(key).build())
                .collect(Collectors.toList());

        // Delete 객체 생성
        Delete delete = Delete.builder()
                .objects(objectIdentifiers) // 삭제할 key 리스트 설정
                .build();

        // S3 삭제 요청
        DeleteObjectsRequest deleteRequest = DeleteObjectsRequest.builder()
                .bucket(s3Manager.getBucketName())
                .delete(delete) // 삭제할 요청 리스트 (최대 1000개)
                .build();

        // 객체 삭제
        s3Client.deleteObjects(deleteRequest);
    }
}
