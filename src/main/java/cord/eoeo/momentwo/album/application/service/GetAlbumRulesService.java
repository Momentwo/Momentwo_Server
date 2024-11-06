package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumRulesResponseDto;
import cord.eoeo.momentwo.album.application.port.in.GetAlbumRulesUseCase;
import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetAlbumRulesService implements GetAlbumRulesUseCase {
    private final GetAlbumMemberInfoPort getAlbumMemberInfoPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional(readOnly = true)
    public AlbumRulesResponseDto getAlbumsRules(long albumId) {
        return new AlbumRulesResponseDto().toDo(getAlbumMemberInfoPort.getMemberInfo(albumId));
    }
}
