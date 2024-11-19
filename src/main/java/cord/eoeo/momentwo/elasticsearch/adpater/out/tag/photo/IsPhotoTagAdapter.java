package cord.eoeo.momentwo.elasticsearch.adpater.out.tag.photo;

import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.IsPhotoTagPort;
import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsPhotoTagAdapter implements IsPhotoTagPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public boolean isPhotoTag(long photoId) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("id",  "tags_" + photoId))
                .build();

        // 검색 결과 가져오기
        SearchHits<PhotoTagsDocument> searchHit = elasticsearchRestTemplate.search(searchQuery, PhotoTagsDocument.class);

        // 비어있다면 태그 정보 없음
        if(searchHit.getSearchHits().isEmpty()) {
            return false;
        }
        return true;
    }
}
