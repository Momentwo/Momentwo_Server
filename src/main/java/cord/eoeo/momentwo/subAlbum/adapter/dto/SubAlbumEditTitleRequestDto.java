package cord.eoeo.momentwo.subAlbum.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubAlbumEditTitleRequestDto {
    private long albumId;
    private long subAlbumId;
    private String editTitle;
}
