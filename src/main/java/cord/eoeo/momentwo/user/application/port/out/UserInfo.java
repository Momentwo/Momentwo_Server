package cord.eoeo.momentwo.user.application.port.out;

import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.out.TempPasswordResponseDto;

public interface UserInfo {
    SearchUsernameResponseDto searchUsername(String name, String phone);

    TempPasswordResponseDto tempPassword(String username, String phone);

    void changePassword(String newPassword, String newPasswordMatch);

    void usersProfilesUpload(String filename);
}
