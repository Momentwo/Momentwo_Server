package cord.eoeo.momentwo.member.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KickMembersRequestDto {
    private List<String> kickMemberList;
}
