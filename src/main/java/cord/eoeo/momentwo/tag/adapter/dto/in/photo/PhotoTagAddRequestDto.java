package cord.eoeo.momentwo.tag.adapter.dto.in.photo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoTagAddRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "사진 아이디 누락")
    private Long photoId;

    @NotNull(message = "앨범 태그 아이디 누락")
    private Long albumTagId;
}
