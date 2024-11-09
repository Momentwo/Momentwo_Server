package cord.eoeo.momentwo.album.application.service.profile;

import cord.eoeo.momentwo.album.adapter.dto.AlbumSubTitleEditRequestDto;
import cord.eoeo.momentwo.album.application.port.in.profile.AlbumSubTitleEditUseCase;
import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberPort;
import cord.eoeo.momentwo.album.application.port.out.profile.AlbumSubTitleEditPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumSubTitleEditService implements AlbumSubTitleEditUseCase {
    private final AlbumSubTitleEditPort albumSubTitleEditPort;
    private final GetAlbumMemberPort getAlbumMemberPort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void albumSubTitleEdit(AlbumSubTitleEditRequestDto subTitleEditRequestDto) {
        albumSubTitleEditPort.albumSubTitleEdit(
                getAlbumMemberPort.getMember(subTitleEditRequestDto.getAlbumId()),
                subTitleEditRequestDto.getSubTitle()
        );
    }
}
