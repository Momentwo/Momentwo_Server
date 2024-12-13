package cord.eoeo.momentwo.tag.application.service.album;

import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagListResponseDto;
import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagGetUseCase;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagGetAllTagPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumTagGetService implements AlbumTagGetUseCase {
    private final AlbumTagGetAllTagPort albumTagGetAllTagPort;

    @Override
    @Transactional(readOnly = true)
    @CheckAlbumAccessRules
    public AlbumTagListResponseDto albumTagGet(long albumId) {
        return new AlbumTagListResponseDto().toDo(
                albumTagGetAllTagPort.allTagByAlbumId(albumId)
        );
    }
}
