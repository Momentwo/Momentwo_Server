package cord.eoeo.momentwo.elasticsearch.application.port.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesStatusSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesStatusSearchListResponseDto;

public interface LikesSearchUseCase {
    LikesSearchListResponseDto getPhotoLikes(LikesSearchRequestDto likesSearchRequestDto);
    LikesStatusSearchListResponseDto getPhotoLikesStatus(LikesStatusSearchRequestDto likesStatusSearchRequestDto);
}
