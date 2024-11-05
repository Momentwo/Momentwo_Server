package cord.eoeo.momentwo.subAlbum.adapter.out.manager;

import cord.eoeo.momentwo.subAlbum.application.port.out.delete.DeleteSubAlbumIdsRepo;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.DeleteSubAlbumPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteSubAlbumPortAdapter implements DeleteSubAlbumPort {
    private final DeleteSubAlbumIdsRepo deleteBySubAlbumIds;

    @Override
    @Transactional
    public void deleteSubAlbum(List<Long> subAlbumIds) {
        deleteBySubAlbumIds.deleteBySubAlbumIds(subAlbumIds);
    }
}
