package cord.eoeo.momentwo.user.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchUsernameRequestDto {
    @Pattern(regexp = "^[a-zA-Zㄱ-ㅎ가-힣]{2,20}", message = "이름은 최대 2~20글자까지 허용됩니다.")
    private String name;

    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "연락처는 0x-xxx-xxxx 또는 0xx-xxxx-xxxx의 형식을 지켜주세요.")
    private String phone;
}
