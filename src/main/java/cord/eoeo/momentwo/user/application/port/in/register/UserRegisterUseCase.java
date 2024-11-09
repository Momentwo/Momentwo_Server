package cord.eoeo.momentwo.user.application.port.in.register;

import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;

public interface UserRegisterUseCase {
    /**
     * 회원가입
     * @param userRegisterRequestDto
     * name : 이름
     * username : 이메일
     * password : 비밀번호
     * nickname : 별명
     * birthday : 생년월일
     * phone : 연락처
     */
    void register(UserRegisterRequestDto userRegisterRequestDto);
}
