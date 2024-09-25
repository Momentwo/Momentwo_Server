package cord.eoeo.momentwo.elasticsearch.application.service;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.LikesSearchUseCase;
import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchManager;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesSearchService implements LikesSearchUseCase {
    private final LikesElasticSearchManager likesElasticSearchManager;

    @Override
    @CheckAlbumAccessRules
    public LikesSearchListResponseDto getPhotoLikes(LikesSearchRequestDto likesSearchRequestDto) {
        Pageable pageable = PageRequest.of(likesSearchRequestDto.getPage(), likesSearchRequestDto.getSize());

        return new LikesSearchListResponseDto()
                .toDo(likesElasticSearchManager.getPhotoLikesPaging(likesSearchRequestDto.getPhotoId(), pageable));
    }
}
