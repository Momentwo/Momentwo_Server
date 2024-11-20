package cord.eoeo.momentwo.tag.application.service.album;

import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagCreateRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagCreateUseCase;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumTagCreateService implements AlbumTagCreateUseCase {
    private final AlbumTagSavePort albumTagSavePort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void albumTagCreate(AlbumTagCreateRequestDto albumTagCreateRequestDto) {
        albumTagSavePort.albumTagSave(
                albumTagCreateRequestDto.getAlbumId(),
                albumTagCreateRequestDto.getTag()
        );
    }
}
