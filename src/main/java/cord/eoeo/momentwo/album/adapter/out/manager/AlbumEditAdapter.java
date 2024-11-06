package cord.eoeo.momentwo.album.adapter.out.manager;

import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.manager.AlbumEditPort;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AlbumEditAdapter implements AlbumEditPort {
    private final AlbumGenericRepo albumGenericRepo;

    @Override
    @Transactional
    public void albumEdit(Member member, String editTitle) {
        member.getAlbum().setTitle(editTitle);
        albumGenericRepo.save(member.getAlbum());
    }
}
