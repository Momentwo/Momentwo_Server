package cord.eoeo.momentwo.member.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KickMembersRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotEmpty(message = "추방 멤버 닉네임 누락")
    private List<String> kickMemberList;
}
