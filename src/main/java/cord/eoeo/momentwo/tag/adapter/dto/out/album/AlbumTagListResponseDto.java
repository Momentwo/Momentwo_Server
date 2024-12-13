package cord.eoeo.momentwo.tag.adapter.dto.out.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumTagListResponseDto {
    private List<AlbumTagQueryDto> albumTags;

    public AlbumTagListResponseDto toDo(List<AlbumTagQueryDto> albumTags) {
        return new AlbumTagListResponseDto(
                albumTags
        );
    }
}
