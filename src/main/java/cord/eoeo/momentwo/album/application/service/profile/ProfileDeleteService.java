package cord.eoeo.momentwo.album.application.service.profile;

import cord.eoeo.momentwo.album.adapter.dto.AlbumProfileRequestDto;
import cord.eoeo.momentwo.album.application.port.in.profile.ProfileDeleteUseCase;
import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberPort;
import cord.eoeo.momentwo.album.application.port.out.profile.ProfileDeletePort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileDeleteService implements ProfileDeleteUseCase {
    private final ProfileDeletePort profileDeletePort;
    private final GetAlbumMemberPort getAlbumMemberPort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void profileDelete(AlbumProfileRequestDto albumProfileRequestDto) {
        profileDeletePort.profileDelete(getAlbumMemberPort.getMember(albumProfileRequestDto.getAlbumId()));
    }
}
