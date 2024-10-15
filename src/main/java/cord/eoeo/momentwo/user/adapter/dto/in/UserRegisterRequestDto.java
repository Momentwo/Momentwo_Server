package cord.eoeo.momentwo.user.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserRegisterRequestDto {
    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Zㄱ-ㅎ가-힣]{2,20}", message = "이름은 최대 2~20글자까지 허용됩니다.")
    private String name;

    @NotBlank(message = "사용할 이메일를 입력해주세요.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            message = "이메일 형식을 지켜주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$",
            message = "비밀번호는 8이상 20자리 특수문자, 영문, 숫자 조합 형식을 지켜주세요.")
    private String password;

    @NotBlank(message = "사용할 별명을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "생일을 입력해주세요.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "생년월일은 xxxx-xx-xx 형식을 지켜주세요.")
    private String birthday;

    @NotBlank(message = "연락처를 입력해주세요.")
    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "연락처는 0x-xxx-xxxx 또는 0xx-xxxx-xxxx의 형식을 지켜주세요.")
    private String phone;
}
