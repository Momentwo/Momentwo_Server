package cord.eoeo.momentwo.user.application.port.in.password;

import cord.eoeo.momentwo.user.adapter.dto.in.ChangePasswordRequestDto;

public interface UserPasswordChangeUseCase {
    /**
     * 유저 비밀번호 변경
     * @param changePasswordRequestDto
     * newPassword : 변경할 비밀번호
     * newPasswordMatch : 변경할 비밀번호 확인
     */
    void changePassword(ChangePasswordRequestDto changePasswordRequestDto);
}
