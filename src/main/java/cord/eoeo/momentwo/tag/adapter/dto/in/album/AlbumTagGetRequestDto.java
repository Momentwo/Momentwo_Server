package cord.eoeo.momentwo.tag.adapter.dto.in.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumTagGetRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;
}
