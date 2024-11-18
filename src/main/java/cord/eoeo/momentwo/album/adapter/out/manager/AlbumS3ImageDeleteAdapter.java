package cord.eoeo.momentwo.album.adapter.out.manager;

import cord.eoeo.momentwo.album.application.port.out.manager.AlbumS3ImageDeletePort;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.application.port.out.ImageListDeletePort;
import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindAllImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class AlbumS3ImageDeleteAdapter implements AlbumS3ImageDeletePort {
    private final PhotoFindAllImagePort photoFindAllImagePort;
    private final ImageListDeletePort imageListDeletePort;
    private final S3Manager s3Manager;

    @Override
    public void s3ImageDelete(long albumId) {
        List<String> keys = photoFindAllImagePort.photoFindAllImage(albumId);
        int batchSize = 1000;

        Map<Integer, List<String>> groupedKeys = IntStream.range(0, keys.size())
                        .mapToObj(idx -> new AbstractMap.SimpleEntry<>(
                                idx / batchSize, // key : 계산에 따른 그룹 id 배치
                                keys.get(idx).substring(s3Manager.getBaseDomain().length()) // value : key 값에 따른 value 접근
                                )
                        )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey, // id : 위에서 정의한 group key
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList()) // value : getValue를 toList로 매핑
                ));

        groupedKeys.forEach((group, batch) -> imageListDeletePort.imageListDelete(batch));
    }
}
