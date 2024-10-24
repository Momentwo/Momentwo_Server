package cord.eoeo.momentwo.elasticsearch.adpater.out;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteByQueryRequest;
import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchManager;
import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesSearchRepository;
import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import cord.eoeo.momentwo.photo.domain.Photo;
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

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class LikesElasticSearchManagerImpl implements LikesElasticSearchManager {
    private final LikesSearchRepository likesSearchRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    @Transactional
    public void save(User user, Photo photo) {
        LikesDocument likesDocument = new LikesDocument(user, photo);
        likesSearchRepository.save(likesDocument);
    }

    @Override
    @Transactional(readOnly = true)
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

        return new PageImpl<>(likesDocuments);
    }

    @Override
    @Transactional
    public void deleteByLikes(long id, String nickname) {
        String deleteDocument = id + "_" + nickname;

        // 클라이언트를 통해 업데이트 요청 실행
        elasticsearchRestTemplate.delete(deleteDocument, IndexCoordinates.of("likes"));
    }

    @Override
    @Transactional(readOnly = true)
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

    @Override
    @Transactional(readOnly = true)
    public Page<LikesDocument> getPhotoLikesStatus(long subAlbumId, String nickname, long minPid, long maxPid) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("subAlbumId", subAlbumId))
                        .must(QueryBuilders.matchQuery("nickname", nickname))
                        .must(QueryBuilders.rangeQuery("photoId").gte(minPid)
                                .lte(maxPid))
                )
                .build();

        // 검색 결과 가져오기
        SearchHits<LikesDocument> searchHit = elasticsearchRestTemplate.search(searchQuery, LikesDocument.class);

        // 검색된 데이터를 리스트로 변환
        List<LikesDocument> likesDocuments = searchHit.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        return new PageImpl<>(likesDocuments);
    }

    @Override
    @Transactional
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
