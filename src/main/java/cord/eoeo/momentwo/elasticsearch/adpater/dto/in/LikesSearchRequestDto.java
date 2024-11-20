package cord.eoeo.momentwo.elasticsearch.adpater.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesSearchRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "사진 아이디 누락")
    private Long photoId;

    @NotNull(message = "페이지 누락")
    private Integer page;

    @NotNull(message = "사이즈 누락")
    private Integer size;
}
