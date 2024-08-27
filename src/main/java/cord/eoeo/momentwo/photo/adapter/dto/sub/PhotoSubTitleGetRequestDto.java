package cord.eoeo.momentwo.photo.adapter.dto.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoSubTitleGetRequestDto {
    private long albumId;
    private long subAlbumId;
}
