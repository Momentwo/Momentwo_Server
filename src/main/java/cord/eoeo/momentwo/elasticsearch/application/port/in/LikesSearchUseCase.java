package cord.eoeo.momentwo.elasticsearch.application.port.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesSearchListResponseDto;

public interface LikesSearchUseCase {
    LikesSearchListResponseDto getPhotoLikes(LikesSearchRequestDto likesSearchRequestDto);
}
