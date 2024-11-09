package cord.eoeo.momentwo.elasticsearch.adpater.out.like;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.LikesElasticSearchMovePhotoPort;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class LikesElasticSearchMovePhotoAdapter implements LikesElasticSearchMovePhotoPort {
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void editSubAlbumId(Photo photo, User user, long moveSubAlbumId) {
        UpdateQuery updateQuery = UpdateQuery.builder(photo.getId() + "_" + user.getNickname())
                .withIndex("likes")
                .withLang("painless")
                .withScript("ctx._source.subAlbumId = params.moveSubAlbumId")
                .withParams(Collections.singletonMap("moveSubAlbumId", moveSubAlbumId))
                .build();

        elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("likes"));
    }
}
