package cord.eoeo.momentwo.user.adapter.dto.out;

import cord.eoeo.momentwo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDto {
    private long id;
    private String username;
    private String nickname;
    private String userProfileImage;

    public UserProfileResponseDto toDo(User user) {
        return new UserProfileResponseDto(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getUserProfileImage()
        );
    }
}
