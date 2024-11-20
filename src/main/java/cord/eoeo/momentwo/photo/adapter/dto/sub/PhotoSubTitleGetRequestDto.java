package cord.eoeo.momentwo.photo.adapter.dto.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoSubTitleGetRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "서브 앨범 아이디 누락")
    private Long subAlbumId;
}
