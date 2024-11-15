package cord.eoeo.momentwo.image.application.service;

import cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin;
import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumInfoPort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.application.port.in.AlbumProfilePresignedUrlUseCase;
import cord.eoeo.momentwo.image.application.port.out.ImageDeletePort;
import cord.eoeo.momentwo.image.application.port.out.MakeImagePresignedUrlPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumProfilePresignedUrlService implements AlbumProfilePresignedUrlUseCase {
    private final GetAlbumInfoPort getAlbumInfoPort;
    private final ImageDeletePort imageDeletePort;
    private final MakeImagePresignedUrlPort makeImagePresignedUrlPort;
    private final S3Manager s3Manager;

    @Override
    @CheckAlbumAdmin
    public PresignedResponseDto albumProfilePresignedUrl(PresignedRequestDto presignedRequestDto) {
        Album album = getAlbumInfoPort.getAlbumInfo(presignedRequestDto.getAlbumId());

        // 이미지 삭제(현재) -> 기존 이미지가 없다면 업로드한 이미지만 저장, 기존 이미지가 있다면 삭제 진행
        imageDeletePort.imageDelete(s3Manager.getProfileAlbumPath() + album.getProfileFilename()).join();
        return new PresignedResponseDto()
                .toDo(makeImagePresignedUrlPort
                        .makeImagePresignedUrl(presignedRequestDto.getExtension(), s3Manager.getProfileAlbumPath()));
    }
}
