package cord.eoeo.momentwo.tag.adapter.out.album.manager;

import cord.eoeo.momentwo.tag.advice.exception.NotFoundTagException;
import cord.eoeo.momentwo.tag.advice.exception.TagDuplicateException;
import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.album.jpa.AlbumTagGetAlbumIdAndTagRepo;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagUpdatePort;
import cord.eoeo.momentwo.tag.domain.AlbumTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumTagUpdateAdapter implements AlbumTagUpdatePort {
    private final AlbumTagGenericRepo albumTagGenericRepo;
    private final AlbumTagGetAlbumIdAndTagRepo albumTagGetAlbumIdAndTagRepo;

    @Override
    public void albumTagUpdate(long albumId, long albumTagId, String tag) {
        // 이미 있는 태그인지 확인
        albumTagGetAlbumIdAndTagRepo.getAlbumIdAndTag(albumId, tag).ifPresentOrElse(
                albumTag -> {
                    throw new TagDuplicateException();
                },
                () -> {
                    AlbumTag albumTag = albumTagGenericRepo.findById(albumTagId).orElseThrow(NotFoundTagException::new);
                    albumTag.setTag(tag);
                    albumTagGenericRepo.save(albumTag);
                }
        );
    }
}
