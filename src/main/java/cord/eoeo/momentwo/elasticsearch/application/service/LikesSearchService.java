package cord.eoeo.momentwo.elasticsearch.application.service;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesStatusSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesStatusSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.LikesSearchUseCase;
import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchManager;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesSearchService implements LikesSearchUseCase {
    private final LikesElasticSearchManager likesElasticSearchManager;
    private final GetAuthentication getAuthentication;

    @Override
    @CheckAlbumAccessRules
    public LikesSearchListResponseDto getPhotoLikes(LikesSearchRequestDto likesSearchRequestDto) {
        Pageable pageable = PageRequest.of(likesSearchRequestDto.getPage(), likesSearchRequestDto.getSize());

        return new LikesSearchListResponseDto()
                .toDo(likesElasticSearchManager.getPhotoLikesPaging(likesSearchRequestDto.getPhotoId(), pageable));
    }

    @Override
    @CheckAlbumAccessRules
    public LikesStatusSearchListResponseDto getPhotoLikesStatus(LikesStatusSearchRequestDto likesStatusSearchRequestDto) {
        Pageable pageable = PageRequest
                .of(likesStatusSearchRequestDto.getPage(), likesStatusSearchRequestDto.getSize());

        return new LikesStatusSearchListResponseDto()
                .toDo(likesElasticSearchManager
                        .getPhotoLikesStatus(
                                likesStatusSearchRequestDto.getSubAlbumId(),
                                getAuthentication.getAuthentication().getName(),
                                pageable)
                );
    }
}
