package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipSendListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.in.GetFriendshipSendUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetFriendshipSendController {
    private final GetFriendshipSendUseCase getFriendshipSendUseCase;

    // 보낸 친구 요청 조회
    @GetMapping("/friendship/send")
    @ResponseStatus(HttpStatus.OK)
    public FriendshipSendListResponseDto getFriendshipSend() {
        return getFriendshipSendUseCase.getFriendshipSend();
    }
}
