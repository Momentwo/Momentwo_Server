package cord.eoeo.momentwo.album.adapter.out.profile;

import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumSubTitlePort;
import cord.eoeo.momentwo.album.application.port.out.profile.AlbumSubTitleDeletePort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AlbumSubTitleDeleteAdapter implements AlbumSubTitleDeletePort {
    private final GetAlbumSubTitlePort getAlbumSubTitlePort;
    private final AlbumGenericRepo albumGenericRepo;

    @Override
    @Transactional
    public void albumSubTitleDelete(Member member) {
        Album album = member.getAlbum();
        album.setSubTitle(getAlbumSubTitlePort.getSubTitle());
        albumGenericRepo.save(album);
    }
}
