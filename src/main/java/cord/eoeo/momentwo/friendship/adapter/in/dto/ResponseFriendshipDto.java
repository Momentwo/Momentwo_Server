package cord.eoeo.momentwo.friendship.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFriendshipDto {
    private String nickname;
    private Boolean accept;
}
