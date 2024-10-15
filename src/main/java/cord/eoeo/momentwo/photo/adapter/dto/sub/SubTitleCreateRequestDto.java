package cord.eoeo.momentwo.photo.adapter.dto.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTitleCreateRequestDto {
    private long albumId;
    private long subAlbumId;
    private String subTitle;
}
