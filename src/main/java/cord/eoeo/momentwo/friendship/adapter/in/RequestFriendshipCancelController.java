package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.application.port.in.RequestFriendshipCancelUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RequestFriendshipCancelController {
    private final RequestFriendshipCancelUseCase requestFriendshipCancelUseCase;

    // 친구 요청 취소
    @DeleteMapping("/friendship/users/{userid}/request")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requestFriendshipCancel(@PathVariable Long userId) {
        requestFriendshipCancelUseCase.requestFriendshipCancel(userId);
    }
}
