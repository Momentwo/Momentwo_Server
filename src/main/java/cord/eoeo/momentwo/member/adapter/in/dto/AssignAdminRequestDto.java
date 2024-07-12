package cord.eoeo.momentwo.member.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignAdminRequestDto {
    @NotBlank(message = "별명을 입력해주세요.")
    private String nickname;
}
