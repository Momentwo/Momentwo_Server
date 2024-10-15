package cord.eoeo.momentwo.user.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NicknameAvailabilityDto {
    @NotBlank(message = "사용할 별명을 입력해주세요.")
    private String nickname;
}
