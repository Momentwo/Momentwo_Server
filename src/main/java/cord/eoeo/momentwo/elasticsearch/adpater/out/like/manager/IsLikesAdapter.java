package cord.eoeo.momentwo.elasticsearch.adpater.out.like.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.IsLikesPort;
import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsLikesAdapter implements IsLikesPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public boolean isLikes(User user, long photoId) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("id", photoId + "_" + user.getNickname()))
                .build();

        // 검색 결과 가져오기
        SearchHits<LikesDocument> searchHit = elasticsearchRestTemplate.search(searchQuery, LikesDocument.class);

        // 비어있다면 좋아요를 누르지 않았음
        if(searchHit.getSearchHits().isEmpty()) {
            return false;
        }
        return true;
    }
}
