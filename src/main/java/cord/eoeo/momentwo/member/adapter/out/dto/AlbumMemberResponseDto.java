package cord.eoeo.momentwo.member.adapter.out.dto;

import cord.eoeo.momentwo.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumMemberResponseDto {
    private List<MemberResponseDto> albumMember;

    public AlbumMemberResponseDto toDo(List<Member> albumMember) {
        return new AlbumMemberResponseDto(
                albumMember.stream().map(member -> new MemberResponseDto().toDo(member))
                        .collect(Collectors.toList())
        );
    }
}
