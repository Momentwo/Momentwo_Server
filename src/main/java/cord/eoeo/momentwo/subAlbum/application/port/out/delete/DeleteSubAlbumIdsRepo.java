package cord.eoeo.momentwo.subAlbum.application.port.out.delete;

import java.util.List;

public interface DeleteSubAlbumIdsRepo {
    void deleteBySubAlbumIds(List<Long> subAlbumIds);
}
