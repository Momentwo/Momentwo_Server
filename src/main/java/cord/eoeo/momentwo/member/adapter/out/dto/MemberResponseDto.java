package cord.eoeo.momentwo.member.adapter.out.dto;

import cord.eoeo.momentwo.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String nickname;
    private String rules;

    public MemberResponseDto toDo(Member member) {
        return new MemberResponseDto(
                member.getUser().getNickname(),
                member.getRules().toString()
        );
    }
}
