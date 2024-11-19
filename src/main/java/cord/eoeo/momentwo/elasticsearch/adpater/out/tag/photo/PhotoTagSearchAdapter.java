package cord.eoeo.momentwo.elasticsearch.adpater.out.tag.photo;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.photo.PhotoTagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagSearchPort;
import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
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
public class PhotoTagSearchAdapter implements PhotoTagSearchPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final PhotoTagElasticsearchRepo photoTagElasticsearchRepo;

    @Override
    public Page<PhotoTagsDocument> photoTagSearch(PhotoTagSearchRequestDto photoTagSearchRequestDto, Pageable pageable) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("albumId", photoTagSearchRequestDto.getAlbumId()))
                        .must(QueryBuilders.termsQuery("photoTagsTerms", photoTagSearchRequestDto.getPhotoTags()))
                )
                .withPageable(pageable)
                .build();

        // 검색 결과 가져오기
        SearchHits<PhotoTagsDocument> searchHit = elasticsearchRestTemplate.search(searchQuery, PhotoTagsDocument.class);

        // 검색된 데이터를 리스트로 변환
        List<PhotoTagsDocument> photoTagsDocuments = searchHit.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        return new PageImpl<>(
                photoTagsDocuments,
                pageable,
                photoTagElasticsearchRepo.countByAlbumId(photoTagSearchRequestDto.getAlbumId())
        );
    }
}
