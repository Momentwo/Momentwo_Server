package cord.eoeo.momentwo.photo.adapter.out;

import cord.eoeo.momentwo.image.application.port.out.ImageListDeletePort;
import cord.eoeo.momentwo.photo.application.port.out.PhotoS3ListDeletePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class PhotoS3ListDeleteAdapter implements PhotoS3ListDeletePort {
    private final ImageListDeletePort imageListDeletePort;

    @Override
    public void photoS3ListDelete(List<String> keys) {
        int batchSize = 1000;

        Map<Integer, List<String>> groupedKeys = IntStream.range(0, keys.size())
                .mapToObj(idx -> new AbstractMap.SimpleEntry<>(
                                idx / batchSize, // key : 계산에 따른 그룹 id 배치
                                keys.get(idx) // value : key 값에 따른 value 접근
                        )
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey, // id : 위에서 정의한 group key
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList()) // value : getValue를 toList로 매핑
                ));

        groupedKeys.forEach((group, batch) -> imageListDeletePort.imageListDelete(batch));
    }
}
