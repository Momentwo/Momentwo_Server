package cord.eoeo.momentwo.tag.application.port.in.photo;

import cord.eoeo.momentwo.tag.adapter.dto.in.photo.PhotoTagAddRequestDto;

public interface PhotoTagAddUseCase {
    void photoTagAdd(PhotoTagAddRequestDto photoTagAddRequestDto);
}
