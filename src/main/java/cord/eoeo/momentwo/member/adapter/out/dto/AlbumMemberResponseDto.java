package cord.eoeo.momentwo.member.adapter.out.dto;

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

    public AlbumMemberResponseDto toDo(List<String> albumMember) {
        return new AlbumMemberResponseDto(
                albumMember.stream().map(nickname -> new MemberResponseDto().toDo(nickname)).collect(Collectors.toList())
        );
    }
}
