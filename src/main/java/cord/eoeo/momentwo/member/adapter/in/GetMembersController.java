package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.adapter.out.dto.AlbumMemberResponseDto;
import cord.eoeo.momentwo.member.application.port.in.GetMembersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetMembersController {
    private final GetMembersUseCase getMembersUseCase;

    // 멤버 조회
    @GetMapping("/albums/{albumId}/members")
    @ResponseStatus(HttpStatus.OK)
    public AlbumMemberResponseDto getMembers(@PathVariable Long albumId) {
        return getMembersUseCase.getMembers(albumId);
    }
}
