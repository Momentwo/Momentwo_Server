package cord.eoeo.momentwo.elasticsearch.adpater.out.user.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.user.manager.UserDeleteByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDeleteByIdAdapter implements UserDeleteByIdPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void deleteById(long id) {
        // 클라이언트를 통해 업데이트 요청 실행
        elasticsearchRestTemplate.delete(String.valueOf(id), IndexCoordinates.of("users"));
    }
}
