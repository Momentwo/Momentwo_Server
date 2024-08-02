package cord.eoeo.momentwo.user.application.service;

import cord.eoeo.momentwo.user.adapter.dto.in.ChangePasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.SearchUsernameRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.TempPasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.out.TempPasswordResponseDto;
import cord.eoeo.momentwo.user.application.port.in.UserInfoChangeUseCase;
import cord.eoeo.momentwo.user.application.port.out.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoChangeService implements UserInfoChangeUseCase {
    private final UserInfo userInfo;

    @Transactional(readOnly = true)
    @Override
    public SearchUsernameResponseDto searchUsername(SearchUsernameRequestDto searchUsernameRequestDto) {
        return userInfo.searchUsername(
                searchUsernameRequestDto.getName(),
                searchUsernameRequestDto.getPhone()
        );
    }

    @Transactional
    @Override
    public TempPasswordResponseDto tempPassword(TempPasswordRequestDto tempPasswordRequestDto) {
        return userInfo.tempPassword(
                tempPasswordRequestDto.getUsername(),
                tempPasswordRequestDto.getPhone()
        );
    }

    @Transactional
    @Override
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        userInfo.changePassword(
                changePasswordRequestDto.getNewPassword(),
                changePasswordRequestDto.getNewPasswordMatch()
        );
    }
}
