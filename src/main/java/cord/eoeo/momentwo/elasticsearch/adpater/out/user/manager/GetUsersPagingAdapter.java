package cord.eoeo.momentwo.elasticsearch.adpater.out.user.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.user.manager.GetUsersPagingPort;
import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetUsersPagingAdapter implements GetUsersPagingPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public Page<UserDocument> getUsersPaging(String keyword, User user, Pageable pageable) {
        NativeSearchQuery countQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.wildcardQuery("nickname", keyword + "*"))
                        .mustNot(QueryBuilders.matchQuery("id", user.getId()))
                )
                .withPageable(pageable)
                .build();

        // Wildcard 쿼리 + 페이징 적용
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("nickname", keyword + "*"))
                .withPageable(pageable)
                .build();

        // 검색 결과 가져오기
        SearchHits<UserDocument> searchHit = elasticsearchRestTemplate.search(searchQuery, UserDocument.class);

        // 검색된 데이터를 리스트로 변환
        List<UserDocument> userDocuments = searchHit.getSearchHits().stream()
                .map(SearchHit::getContent)
                .filter(userDocument -> !Objects.equals(userDocument.getId(), user.getId()))
                .collect(Collectors.toList());

        long count = elasticsearchRestTemplate.count(countQuery, UserDocument.class);

        return new PageImpl<>(userDocuments, pageable, count);
    }
}
