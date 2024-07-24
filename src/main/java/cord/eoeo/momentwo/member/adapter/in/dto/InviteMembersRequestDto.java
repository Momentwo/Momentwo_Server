package cord.eoeo.momentwo.member.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteMembersRequestDto {
    @NotEmpty(message = "초대할 유저 닉네임을 하나이상 입력해주세요.")
    private List<String> inviteNicknames;
}
