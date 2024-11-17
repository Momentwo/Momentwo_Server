package cord.eoeo.momentwo.elasticsearch.adpater.out.like.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.GetPhotoLikesStatusPort;
import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class GetPhotoLikesStatusAdapter implements GetPhotoLikesStatusPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public Page<LikesDocument> getPhotoLikesStatus(long subAlbumId, String nickname, long minPid, long maxPid) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("subAlbumId", subAlbumId))
                        .must(QueryBuilders.matchQuery("nickname", nickname))
                        .must(QueryBuilders.rangeQuery("photoId").gte(minPid).lte(maxPid))
                )
                .withPageable(PageRequest.of(0, 20))
                .build();

        // 검색 결과 가져오기
        SearchHits<LikesDocument> searchHit = elasticsearchRestTemplate.search(searchQuery, LikesDocument.class);

        // 검색된 데이터를 리스트로 변환
        List<LikesDocument> likesDocuments = searchHit.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        return new PageImpl<>(likesDocuments);
    }
}
