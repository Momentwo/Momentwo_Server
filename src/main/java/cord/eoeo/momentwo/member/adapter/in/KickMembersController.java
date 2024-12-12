package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.application.port.in.KickMembersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KickMembersController {
    private final KickMembersUseCase kickMembersUseCase;

    // 멤버 추방
    @DeleteMapping("/albums/{albumId}/members/kick/{kickUsersId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void kickMembers(@PathVariable Long albumId, @PathVariable List<Long> kickUsersId) {
        kickMembersUseCase.kickMembers(albumId, kickUsersId);
    }
}
