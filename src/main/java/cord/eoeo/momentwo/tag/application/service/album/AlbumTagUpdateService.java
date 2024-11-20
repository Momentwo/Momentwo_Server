package cord.eoeo.momentwo.tag.application.service.album;

import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagUpdateRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagUpdateUseCase;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagUpdatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumTagUpdateService implements AlbumTagUpdateUseCase {
    private final AlbumTagUpdatePort albumTagUpdatePort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void albumTagUpdate(AlbumTagUpdateRequestDto albumTagUpdateRequestDto) {
        albumTagUpdatePort.albumTagUpdate(
                albumTagUpdateRequestDto.getAlbumId(),
                albumTagUpdateRequestDto.getAlbumTagId(),
                albumTagUpdateRequestDto.getEditTag()
        );
    }
}
