package cord.eoeo.momentwo.member.adapter.out.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String nickname;

    public MemberResponseDto toDo(String nickname) {
        return new MemberResponseDto(nickname);
    }
}
