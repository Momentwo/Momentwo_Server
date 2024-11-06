package cord.eoeo.momentwo.album.adapter.out.profile;

import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.profile.AlbumSubTitleEditPort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AlbumSubTitleEditAdapter implements AlbumSubTitleEditPort {
    private final AlbumGenericRepo albumGenericRepo;

    @Override
    @Transactional
    public void albumSubTitleEdit(Member member, String subTitle) {
        Album album = member.getAlbum();
        album.setSubTitle(subTitle);
        albumGenericRepo.save(album);
    }
}
