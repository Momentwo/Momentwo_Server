package cord.eoeo.momentwo.subAlbum.application.port.out;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;

import java.util.List;

public interface SubAlbumManager {
    SubAlbum getSubAlbumInfo(long subAlbumId);
    SubAlbumListResponseDto getSubAlbumList(long albumId);
    void setSubAlbumTitle(long subAlbumId, String editTitle);
    void deleteSubAlbum(List<Long> subAlbumIds);
}
