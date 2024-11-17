package cord.eoeo.momentwo.elasticsearch.adpater.out.like.manager;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteByQueryRequest;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.DeleteByWildNicknamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DeleteByWildNicknameAdapter implements DeleteByWildNicknamePort {
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public void deleteByWildNickname(String nickname) {
        DeleteByQueryRequest request = DeleteByQueryRequest.of(
                d -> d.index("likes")
                        .query(q -> q
                                .wildcard(w -> w
                                        .field("id")  // _id 필드를 대상으로 쿼리
                                        .value("*" + nickname)  // "테스터"로 끝나는 ID들 매칭
                                )
                        )
        );
        try {
            elasticsearchClient.deleteByQuery(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
