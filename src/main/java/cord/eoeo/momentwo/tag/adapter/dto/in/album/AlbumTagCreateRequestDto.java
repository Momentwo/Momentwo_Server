package cord.eoeo.momentwo.tag.adapter.dto.in.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumTagCreateRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotBlank(message = "태그명 누락")
    private String tag;
}
