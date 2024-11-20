package cord.eoeo.momentwo.user.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempPasswordRequestDto {
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            message = "이메일 형식을 지켜주세요.")
    @NotBlank(message = "이메일 누락")
    private String username;

    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "연락처는 0x-xxx-xxxx 또는 0xx-xxxx-xxxx의 형식을 지켜주세요.")
    @NotBlank(message = "연락처 누락")
    private String phone;
}
