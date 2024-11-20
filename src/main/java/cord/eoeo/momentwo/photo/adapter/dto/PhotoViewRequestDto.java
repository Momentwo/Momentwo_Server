package cord.eoeo.momentwo.photo.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoViewRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "서브앨범 아이디 누락")
    private Long subAlbumId;

    @NotNull(message = "커서 아이디 누락")
    private Long cursor;
}
