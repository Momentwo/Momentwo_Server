package cord.eoeo.momentwo.elasticsearch.adpater.out.like.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.DeleteByLikesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteByLikesAdapter implements DeleteByLikesPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void deleteByLikes(long id, String nickname) {
        String deleteDocument = id + "_" + nickname;

        // 클라이언트를 통해 업데이트 요청 실행
        elasticsearchRestTemplate.delete(deleteDocument, IndexCoordinates.of("likes"));
    }
}
