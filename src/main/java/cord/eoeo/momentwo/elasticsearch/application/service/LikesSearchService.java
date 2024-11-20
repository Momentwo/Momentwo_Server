package cord.eoeo.momentwo.elasticsearch.application.service;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesStatusSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesStatusSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.LikesSearchUseCase;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.GetPhotoLikesPagingPort;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.GetPhotoLikesStatusPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesSearchService implements LikesSearchUseCase {
    private final GetPhotoLikesStatusPort getPhotoLikesStatusPort;
    private final GetPhotoLikesPagingPort getPhotoLikesPagingPort;
    private final GetAuthentication getAuthentication;

    @Override
    @CheckAlbumAccessRules
    public LikesSearchListResponseDto getPhotoLikes(LikesSearchRequestDto likesSearchRequestDto) {
        Pageable pageable = PageRequest.of(likesSearchRequestDto.getPage(), likesSearchRequestDto.getSize());

        return new LikesSearchListResponseDto()
                .toDo(getPhotoLikesPagingPort.getPhotoLikesPaging(likesSearchRequestDto.getPhotoId(), pageable));
    }

    @Override
    @CheckAlbumAccessRules
    public LikesStatusSearchListResponseDto getPhotoLikesStatus(LikesStatusSearchRequestDto likesStatusSearchRequestDto) {
        return new LikesStatusSearchListResponseDto()
                .toDo(getPhotoLikesStatusPort
                        .getPhotoLikesStatus(
                                likesStatusSearchRequestDto.getSubAlbumId(),
                                getAuthentication.getAuthentication().getName(),
                                likesStatusSearchRequestDto.getMinPid(),
                                likesStatusSearchRequestDto.getMaxPid()
                        )
                );
    }
}
