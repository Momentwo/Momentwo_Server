package cord.eoeo.momentwo.elasticsearch.adpater.out;

import cord.eoeo.momentwo.elasticsearch.application.port.out.UserElasticSearchManager;
import cord.eoeo.momentwo.elasticsearch.application.port.out.UserSearchRepository;
import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class UserElasticSearchManagerImpl implements UserElasticSearchManager {
    private final UserSearchRepository userSearchRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    @Transactional
    public void save(User user) {
        UserDocument userDocument = new UserDocument(user);
        userSearchRepository.save(userDocument);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDocument> getUsersPaging(String keyword, User user, Pageable pageable) {
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

        return new PageImpl<>(userDocuments);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        // 클라이언트를 통해 업데이트 요청 실행
        elasticsearchRestTemplate.delete(String.valueOf(id), IndexCoordinates.of("users"));
    }
}
