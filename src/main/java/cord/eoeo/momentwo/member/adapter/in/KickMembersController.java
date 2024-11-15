package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.adapter.in.dto.KickMembersRequestDto;
import cord.eoeo.momentwo.member.application.port.in.KickMembersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class KickMembersController {
    private final KickMembersUseCase kickMembersUseCase;

    // 멤버 추방
    @DeleteMapping("/members/kick")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void kickMembers(@RequestBody @Valid KickMembersRequestDto kickMembersRequestDto) {
        kickMembersUseCase.kickMembers(kickMembersRequestDto);
    }
}
