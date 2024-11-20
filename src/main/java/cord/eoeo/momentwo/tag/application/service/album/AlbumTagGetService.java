package cord.eoeo.momentwo.tag.application.service.album;

import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagGetRequestDto;
import cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagGetResponseDto;
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
    public AlbumTagGetResponseDto albumTagGet(AlbumTagGetRequestDto albumTagGetRequestDto) {
        return new AlbumTagGetResponseDto().toDo(
                albumTagGetAllTagPort.allTagByAlbumId(albumTagGetRequestDto.getAlbumId())
        );
    }
}
