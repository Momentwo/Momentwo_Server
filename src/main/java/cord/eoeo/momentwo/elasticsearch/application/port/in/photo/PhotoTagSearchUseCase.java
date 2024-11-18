package cord.eoeo.momentwo.elasticsearch.application.port.in.photo;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.photo.PhotoTagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.photo.PhotoTagSearchListResponseDto;

public interface PhotoTagSearchUseCase {
    PhotoTagSearchListResponseDto photoTagSearch(PhotoTagSearchRequestDto photoTagSearchRequestDto);
}
