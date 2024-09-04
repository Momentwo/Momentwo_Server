package cord.eoeo.momentwo.album.adapter.dto;

import cord.eoeo.momentwo.album.domain.Album;
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

    public AlbumInfoListResponseDto toDo(List<Album> albums) {
        return new AlbumInfoListResponseDto(
                albums.stream().map(album -> new AlbumInfoResponseDto().toDo(album))
                        .collect(Collectors.toList())
        );
    }
}
