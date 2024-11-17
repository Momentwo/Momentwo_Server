package cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import org.springframework.data.domain.Page;

public interface GetPhotoLikesStatusPort {
    Page<LikesDocument> getPhotoLikesStatus(long subAlbumId, String nickname, long minPid, long maxPid);
}
