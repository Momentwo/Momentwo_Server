package cord.eoeo.momentwo.user.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserLoginRequestDto {
    // id
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            message = "이메일 형식을 지켜주세요.")
    private String username;
    // password
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$",
            message = "비밀번호는 8이상 20자리 특수문자, 영문, 숫자 조합 형식을 지켜주세요.")
    private String password;
}
