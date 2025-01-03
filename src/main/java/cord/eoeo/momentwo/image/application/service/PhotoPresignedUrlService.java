package cord.eoeo.momentwo.image.application.service;

import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumInfoPort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.application.port.in.PhotoPresignedUrlUseCase;
import cord.eoeo.momentwo.image.application.port.out.MakeImagePresignedUrlPort;
import cord.eoeo.momentwo.photo.advice.exception.PhotoCapacityFullException;
import cord.eoeo.momentwo.photo.application.port.out.get.PhotoGetAlbumCountPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoPresignedUrlService implements PhotoPresignedUrlUseCase {
    private final GetAlbumInfoPort getAlbumInfoPort;
    private final PhotoGetAlbumCountPort photoGetAlbumCountPort;
    private final MakeImagePresignedUrlPort makeImagePresignedUrlPort;
    private final S3Manager s3Manager;

    @Override
    @CheckAlbumAccessRules
    public PresignedResponseDto photoPresignedUrl(PresignedRequestDto presignedRequestDto) {
        // 앨범 제한 갯수를 넘지 않는 다면 반환
        Album album = getAlbumInfoPort.getAlbumInfo(presignedRequestDto.getAlbumId());
        if(photoGetAlbumCountPort.getAlbumCount(album) >= 1000) {
            throw new PhotoCapacityFullException();
        }
        return new PresignedResponseDto()
                .toDo(makeImagePresignedUrlPort
                        .makeImagePresignedUrl(presignedRequestDto.getExtension(), s3Manager.getImagePath()));
    }
}
