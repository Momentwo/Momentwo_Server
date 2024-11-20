package cord.eoeo.momentwo.tag.application.port.out.album.manager;

public interface AlbumTagUpdatePort {
    void albumTagUpdate(long albumId, long albumTagId, String tag);
}
