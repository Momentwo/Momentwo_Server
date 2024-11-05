package cord.eoeo.momentwo.subAlbum.adapter.out.delete;

import cord.eoeo.momentwo.subAlbum.application.port.out.delete.DeleteSubAlbumIdsRepo;
import cord.eoeo.momentwo.subAlbum.application.port.out.jpa.SubAlbumDeleteJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteSubAlbumIdsRepoAdapter implements DeleteSubAlbumIdsRepo {
    private final SubAlbumDeleteJpaRepo subAlbumDeleteJpaRepo;

    @Override
    public void deleteBySubAlbumIds(List<Long> subAlbumIds) {
        subAlbumDeleteJpaRepo.deleteBySubAlbumIds(subAlbumIds);
    }
}
