package cord.eoeo.momentwo.subAlbum.adapter.out.get;

import cord.eoeo.momentwo.subAlbum.application.port.out.get.GetSubAlbumListByAlbumIdRepo;
import cord.eoeo.momentwo.subAlbum.application.port.out.jpa.SubAlbumGetJpaRepo;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetSubAlbumListByAlbumIdRepoAdapter implements GetSubAlbumListByAlbumIdRepo {
    private final SubAlbumGetJpaRepo subAlbumGetJpaRepo;

    @Override
    public List<SubAlbum> getSubAlbumListByAlbumId(long albumId) {
        return subAlbumGetJpaRepo.getSubAlbumListByAlbumId(albumId);
    }
}
