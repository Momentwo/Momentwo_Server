package cord.eoeo.momentwo.subAlbum.application.port.out.manager;

import java.util.List;

public interface DeleteSubAlbumPort {
    void deleteSubAlbum(List<Long> subAlbumIds);
}
