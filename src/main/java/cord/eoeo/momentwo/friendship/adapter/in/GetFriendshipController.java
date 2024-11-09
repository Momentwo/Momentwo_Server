package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipAllListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.in.GetFriendshipUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetFriendshipController {
    private final GetFriendshipUseCase getFriendshipUseCase;

    // 친구목록 조회
    @GetMapping("/friendship")
    @ResponseStatus(HttpStatus.OK)
    public FriendshipAllListResponseDto getFriendship() {
        return getFriendshipUseCase.getFriendship();
    }
}
