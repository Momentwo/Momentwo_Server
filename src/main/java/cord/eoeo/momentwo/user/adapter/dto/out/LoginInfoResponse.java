package cord.eoeo.momentwo.user.adapter.dto.out;

import cord.eoeo.momentwo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoResponse {
    private String name;
    private String username;
    private String nickname;
    private String phone;
    private String userProfileImage;

    public LoginInfoResponse toDo(User user) {
        return new LoginInfoResponse(
                user.getName(),
                user.getUsername(),
                user.getNickname(),
                user.getPhone(),
                user.getUserProfileImage()
        );
    }
}
