package cord.eoeo.momentwo.album.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumSubTitleEditRequestDto {
    private long albumId;
    private String subTitle;
}
