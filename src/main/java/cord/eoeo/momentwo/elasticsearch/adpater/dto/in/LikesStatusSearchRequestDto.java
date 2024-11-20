package cord.eoeo.momentwo.elasticsearch.adpater.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesStatusSearchRequestDto {
    @NotNull(message = "서브 앨범 아이디 누락")
    private Long subAlbumId;

    @NotNull(message = "맨 앞 사진 아이디 누락")
    private Long minPid;

    @NotNull(message = "맨 뒤 사진 아이디 누락")
    private Long maxPid;
}
