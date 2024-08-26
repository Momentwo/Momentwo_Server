package cord.eoeo.momentwo.subAlbum.adapter.dto;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubAlbumListResponseDto {
    private List<SubAlbumResponseDto> subAlbumList;

    public SubAlbumListResponseDto toDo(List<SubAlbum> subAlbums) {
        return new SubAlbumListResponseDto(
                subAlbums.stream().map(subAlbum -> new SubAlbumResponseDto().toDo(subAlbum))
                        .collect(Collectors.toList())
        );
    }
}
