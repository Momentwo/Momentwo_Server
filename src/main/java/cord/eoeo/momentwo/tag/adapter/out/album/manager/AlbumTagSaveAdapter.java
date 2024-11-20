package cord.eoeo.momentwo.tag.adapter.out.album.manager;

import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.tag.advice.exception.TagDuplicateException;
import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.album.jpa.AlbumTagGetAlbumIdAndTagRepo;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagSavePort;
import cord.eoeo.momentwo.tag.domain.AlbumTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumTagSaveAdapter implements AlbumTagSavePort {
    private final AlbumGenericRepo albumGenericRepo;
    private final AlbumTagGenericRepo albumTagGenericRepo;
    private final AlbumTagGetAlbumIdAndTagRepo albumTagGetAlbumIdAndTagRepo;

    @Override
    public void albumTagSave(long albumId, String tag) {
        // 앨범이 있는지 확인
        Album album = albumGenericRepo.findById(albumId).orElseThrow(NotFoundAlbumException::new);

        // 이미 있는 태그인지 확인
        albumTagGetAlbumIdAndTagRepo.getAlbumIdAndTag(albumId, tag).ifPresentOrElse(
                albumTag -> {
                    throw new TagDuplicateException();
                },
                () -> {
                    albumTagGenericRepo.save(new AlbumTag(album, tag));
                }
        );
    }
}
