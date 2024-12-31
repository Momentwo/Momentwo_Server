package cord.eoeo.momentwo.tag.adapter.dto.out.album;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumTagQueryDto {
    private Long id;
    private String tagName;

    public AlbumTagQueryDto(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }
}
