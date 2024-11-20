package cord.eoeo.momentwo.photo.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoMoveRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "이동할 서브앨범 아이디 누락")
    private Long moveSubAlbumId;

    @NotNull(message = "사진 아이디 누락")
    private Long photoId;
}
