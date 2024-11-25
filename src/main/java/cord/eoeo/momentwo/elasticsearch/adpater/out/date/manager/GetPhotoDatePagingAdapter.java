package cord.eoeo.momentwo.elasticsearch.adpater.out.date.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.date.manager.GetPhotoDatePagingPort;
import cord.eoeo.momentwo.elasticsearch.domain.DateDocument;
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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetPhotoDatePagingAdapter implements GetPhotoDatePagingPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public Page<DateDocument> getPhotoDatePaging
            (long subAlbumId, LocalDate startTime, LocalDate endTime, Pageable pageable) {
        NativeSearchQuery countQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("subAlbumId", subAlbumId))
                        .must(QueryBuilders.rangeQuery("date").gte(startTime).lte(endTime))
                )
                .build();

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("subAlbumId", subAlbumId))
                        .must(QueryBuilders.rangeQuery("date").gte(startTime).lte(endTime))
                ).withPageable(pageable)
                .build();

        SearchHits<DateDocument> searchHits = elasticsearchRestTemplate.search(searchQuery, DateDocument.class);

        List<DateDocument> dateDocuments = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        long count = elasticsearchRestTemplate.count(countQuery, DateDocument.class);

        return new PageImpl<>(dateDocuments, pageable, count);
    }
}
