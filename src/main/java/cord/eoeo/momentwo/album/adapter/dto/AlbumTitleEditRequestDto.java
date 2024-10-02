package cord.eoeo.momentwo.album.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumTitleEditRequestDto {
    private long albumId;
    private String editTitle;
}
