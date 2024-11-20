package cord.eoeo.momentwo.tag.adapter.dto.out.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumTagGetResponseDto {
    private List<String> albumTags;

    public AlbumTagGetResponseDto toDo(List<String> albumTags) {
        return new AlbumTagGetResponseDto(
                albumTags
        );
    }
}
