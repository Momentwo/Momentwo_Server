package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.adapter.in.dto.InviteMembersRequestDto;
import cord.eoeo.momentwo.member.application.port.in.InviteMembersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class InviteMembersController {
    private final InviteMembersUseCase inviteMembersUseCase;

    // 멤버 초대
    @PostMapping("/members/invite")
    @ResponseStatus(HttpStatus.OK)
    public void inviteMembers(@RequestBody @Valid InviteMembersRequestDto inviteMembersRequestDto) {
        inviteMembersUseCase.inviteMembers(inviteMembersRequestDto);
    }
}
