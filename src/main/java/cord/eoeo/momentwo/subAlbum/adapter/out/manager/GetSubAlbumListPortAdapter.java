package cord.eoeo.momentwo.subAlbum.adapter.out.manager;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;
import cord.eoeo.momentwo.subAlbum.application.port.out.get.GetSubAlbumListByAlbumIdRepo;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.GetSubAlbumListPort;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetSubAlbumListPortAdapter implements GetSubAlbumListPort {
    private final GetSubAlbumListByAlbumIdRepo getSubAlbumListByAlbumId;

    @Override
    @Transactional(readOnly = true)
    public SubAlbumListResponseDto getSubAlbumList(long albumId) {
        List<SubAlbum> subAlbumList = getSubAlbumListByAlbumId.getSubAlbumListByAlbumId(albumId);
        return new SubAlbumListResponseDto().toDo(subAlbumList);
    }
}
