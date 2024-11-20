package cord.eoeo.momentwo.tag.application.port.in.photo;

import cord.eoeo.momentwo.tag.adapter.dto.in.photo.PhotoTagRemoveRequestDto;

public interface PhotoTagRemoveUseCase {
    void photoTagRemove(PhotoTagRemoveRequestDto photoTagRemoveRequestDto);
}
