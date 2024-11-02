package cord.eoeo.momentwo.user.application.port.in.search;

import cord.eoeo.momentwo.user.adapter.dto.in.SearchUsernameRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;

public interface UsernameSearchUseCase {
    /**
     * 아이디 찾기
     * @param searchUsernameRequestDto
     * name : 이름
     * phone : 연락처
     * @return : 아이디 반환
     */
    SearchUsernameResponseDto usernameSearch(SearchUsernameRequestDto searchUsernameRequestDto);
}
