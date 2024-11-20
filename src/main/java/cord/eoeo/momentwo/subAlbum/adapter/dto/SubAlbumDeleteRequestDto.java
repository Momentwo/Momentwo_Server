package cord.eoeo.momentwo.subAlbum.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubAlbumDeleteRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotEmpty(message = "서브 앨범 아이디 누락")
    private List<Long> subAlbumIds;
}
