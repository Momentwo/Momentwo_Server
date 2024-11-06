package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumTitleEditRequestDto;
import cord.eoeo.momentwo.album.application.port.in.EditAlbumTitleUseCase;
import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.album.application.port.out.manager.AlbumEditPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditAlbumTitleService implements EditAlbumTitleUseCase {
    private final AlbumEditPort albumEditPort;
    private final GetAlbumMemberInfoPort getAlbumMemberInfoPort;

    @Transactional
    @CheckAlbumAccessRules
    @Override
    public void editAlbumsTitle(AlbumTitleEditRequestDto albumTitleEditRequestDto) {
        albumEditPort.albumEdit(
                getAlbumMemberInfoPort.getMemberInfo(albumTitleEditRequestDto.getAlbumId()),
                albumTitleEditRequestDto.getEditTitle()
        );
    }
}
