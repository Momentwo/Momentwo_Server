package cord.eoeo.momentwo.elasticsearch.application.service.photo;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.photo.PhotoTagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.photo.PhotoTagSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.photo.PhotoTagSearchUseCase;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagSearchPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoTagSearchService implements PhotoTagSearchUseCase {
    private final PhotoTagSearchPort photoTagSearchPort;

    @Override
    @CheckAlbumAccessRules
    public PhotoTagSearchListResponseDto photoTagSearch(PhotoTagSearchRequestDto photoTagSearchRequestDto) {
        return new PhotoTagSearchListResponseDto()
                .toDo(photoTagSearchPort.photoTagSearch(
                        photoTagSearchRequestDto,
                        PageRequest.of(photoTagSearchRequestDto.getPage(), photoTagSearchRequestDto.getSize())
                    )
                );
    }
}
