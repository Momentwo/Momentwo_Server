package cord.eoeo.momentwo.user.application.port.in.status;

import cord.eoeo.momentwo.user.adapter.dto.in.SignOutRequestDto;

public interface UserSignOutUseCase {
    /**
     * 회원탈퇴
     * @param signOutRequestDto
     * password : 비밀번호 입력
     */
    void signOut(SignOutRequestDto signOutRequestDto);
}
