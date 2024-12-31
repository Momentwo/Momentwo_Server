package cord.eoeo.momentwo.tag.application.port.in.photo;

public interface PhotoTagRemoveUseCase {
    void photoTagRemove(Long albumId, Long photoId, Long photoTagId);
}
