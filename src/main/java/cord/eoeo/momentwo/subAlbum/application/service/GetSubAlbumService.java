package cord.eoeo.momentwo.subAlbum.application.service;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.in.GetSubAlbumUseCase;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.GetSubAlbumListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSubAlbumService implements GetSubAlbumUseCase {
    private final GetSubAlbumListPort getSubAlbumListPort;

    @Override
    @CheckAlbumAccessRules
    public SubAlbumListResponseDto getSubAlbums(long albumId) {
        return getSubAlbumListPort.getSubAlbumList(albumId);
    }
}
