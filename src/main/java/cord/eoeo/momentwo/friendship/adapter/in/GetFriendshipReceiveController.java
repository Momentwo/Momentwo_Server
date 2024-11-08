package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipReceiveListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.in.GetFriendshipReceiveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetFriendshipReceiveController {
    private final GetFriendshipReceiveUseCase getFriendshipReceiveUseCase;

    // 받은 친구 요청 조회
    @GetMapping("/friendship/receive")
    @ResponseStatus(HttpStatus.OK)
    public FriendshipReceiveListResponseDto getFriendshipReceive() {
        return getFriendshipReceiveUseCase.getFriendshipReceive();
    }
}
