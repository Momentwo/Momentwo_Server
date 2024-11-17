package cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetPhotoLikesPagingPort {
    Page<LikesDocument> getPhotoLikesPaging(long photoId, Pageable pageable);
}
