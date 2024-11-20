package cord.eoeo.momentwo.friendship.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFriendshipDto {
    @NotBlank(message = "닉네임 누락")
    private String nickname;
}
