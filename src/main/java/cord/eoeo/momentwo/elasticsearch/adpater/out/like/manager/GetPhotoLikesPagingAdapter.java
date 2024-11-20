package cord.eoeo.momentwo.elasticsearch.adpater.out.like.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.LikesElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.GetPhotoLikesPagingPort;
import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
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
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetPhotoLikesPagingAdapter implements GetPhotoLikesPagingPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final LikesElasticsearchRepo likesElasticsearchRepo;
    @Override
    public Page<LikesDocument> getPhotoLikesPaging(long photoId, Pageable pageable) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("photoId", photoId))
                .withPageable(pageable)
                .build();

        // 검색 결과 가져오기
        SearchHits<LikesDocument> searchHit = elasticsearchRestTemplate.search(searchQuery, LikesDocument.class);

        // 검색된 데이터를 리스트로 변환
        List<LikesDocument> likesDocuments = searchHit.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        return new PageImpl<>(likesDocuments, pageable, likesElasticsearchRepo.countByPhotoId(photoId));
    }
}
