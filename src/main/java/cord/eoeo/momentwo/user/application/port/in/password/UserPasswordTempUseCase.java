package cord.eoeo.momentwo.user.application.port.in.password;

import cord.eoeo.momentwo.user.adapter.dto.in.TempPasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.TempPasswordResponseDto;

public interface UserPasswordTempUseCase {
    /**
     * 유저 임시 비밀번호 발급
     * @param tempPasswordRequestDto
     * username : 이메일
     * phone : 연락처
     * @return : 임시 비밀번호 발급
     */
    TempPasswordResponseDto tempPassword(TempPasswordRequestDto tempPasswordRequestDto);
}
