package cord.eoeo.momentwo.tag.adapter.dto.in.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumTagUpdateRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "앨범 태그 아이디 누락")
    private Long albumTagId;

    @NotBlank(message = "바꿀 태그 이름 누락")
    private String editTag;
}
