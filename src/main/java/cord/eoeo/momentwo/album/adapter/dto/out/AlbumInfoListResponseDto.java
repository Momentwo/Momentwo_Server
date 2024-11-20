package cord.eoeo.momentwo.album.adapter.dto.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.photo.application.port.out.get.PhotoGetAlbumCountPort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumInfoListResponseDto {
    private List<AlbumInfoResponseDto> albumInfoList;

    public AlbumInfoListResponseDto toDo(List<Album> albums, PhotoGetAlbumCountPort photoGetAlbumCountPort) {
        return new AlbumInfoListResponseDto(
                albums.stream().map(album -> new AlbumInfoResponseDto()
                                .toDo(album, photoGetAlbumCountPort.getAlbumCount(album)))
                        .collect(Collectors.toList())
        );
    }
}
