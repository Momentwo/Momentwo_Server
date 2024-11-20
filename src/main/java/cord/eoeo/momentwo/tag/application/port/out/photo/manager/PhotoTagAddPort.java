package cord.eoeo.momentwo.tag.application.port.out.photo.manager;

public interface PhotoTagAddPort {
    void PhotoTagAdd(long albumId, long photoId, long albumTagId);
}
