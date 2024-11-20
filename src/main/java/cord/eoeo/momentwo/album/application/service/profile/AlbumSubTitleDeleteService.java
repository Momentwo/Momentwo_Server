package cord.eoeo.momentwo.album.application.service.profile;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumProfileRequestDto;
import cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin;
import cord.eoeo.momentwo.album.application.port.in.profile.AlbumSubTitleDeleteUseCase;
import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberPort;
import cord.eoeo.momentwo.album.application.port.out.profile.AlbumSubTitleDeletePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumSubTitleDeleteService implements AlbumSubTitleDeleteUseCase {
    private final AlbumSubTitleDeletePort albumSubTitleDeletePort;
    private final GetAlbumMemberPort getAlbumMemberPort;

    @Override
    @Transactional
    @CheckAlbumAdmin
    public void albumSubTitleDelete(AlbumProfileRequestDto albumProfileRequestDto) {
        albumSubTitleDeletePort.albumSubTitleDelete(getAlbumMemberPort.getMember(albumProfileRequestDto.getAlbumId()));
    }
}
