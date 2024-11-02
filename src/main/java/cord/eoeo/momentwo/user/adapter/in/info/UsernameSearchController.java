package cord.eoeo.momentwo.user.adapter.in.info;

import cord.eoeo.momentwo.user.adapter.dto.in.SearchUsernameRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;
import cord.eoeo.momentwo.user.application.port.in.search.UsernameSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsernameSearchController {
    private final UsernameSearchUseCase usernameSearchUseCase;
    // 아이디 찾기
    @PostMapping("/search/username")
    @ResponseStatus(HttpStatus.OK)
    public SearchUsernameResponseDto usernameSearch(@RequestBody @Valid SearchUsernameRequestDto searchUsernameRequestDto) {
        return usernameSearchUseCase.usernameSearch(searchUsernameRequestDto);
    }
}
