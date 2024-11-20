package cord.eoeo.momentwo.elasticsearch.adpater.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchRequestDto {
    @NotBlank(message = "닉네임 누락")
    private String nickname;

    @NotNull(message = "페이지 누락")
    private Integer page;

    @NotNull(message = "사이즈 누락")
    private Integer size;
}
