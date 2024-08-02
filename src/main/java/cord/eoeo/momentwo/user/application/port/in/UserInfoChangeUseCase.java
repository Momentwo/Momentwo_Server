package cord.eoeo.momentwo.user.application.port.in;

import cord.eoeo.momentwo.user.adapter.dto.in.ChangePasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.SearchUsernameRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.TempPasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.out.TempPasswordResponseDto;

public interface UserInfoChangeUseCase {
    SearchUsernameResponseDto searchUsername(SearchUsernameRequestDto searchUsernameRequestDto);

    TempPasswordResponseDto tempPassword(TempPasswordRequestDto tempPasswordRequestDto);

    void changePassword(ChangePasswordRequestDto changePasswordRequestDto);
}
