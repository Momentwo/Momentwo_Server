package cord.eoeo.momentwo.tag.application.service.album;

import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagDeleteRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagDeleteUseCase;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagDeletePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumTagDeleteService implements AlbumTagDeleteUseCase {
    private final AlbumTagDeletePort albumTagDeletePort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void albumTagDelete(AlbumTagDeleteRequestDto albumTagDeleteRequestDto) {
        albumTagDeletePort.albumTagDelete(albumTagDeleteRequestDto.getAlbumTagId());
    }
}
