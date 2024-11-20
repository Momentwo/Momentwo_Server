package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;
import cord.eoeo.momentwo.friendship.application.port.in.RequestFriendshipCancelUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RequestFriendshipCancelController {
    private final RequestFriendshipCancelUseCase requestFriendshipCancelUseCase;

    // 친구 요청 취소
    @DeleteMapping("/friendship/request/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requestFriendshipCancel(@ModelAttribute @Valid RequestFriendshipDto requestFriendshipDto) {
        requestFriendshipCancelUseCase.requestFriendshipCancel(requestFriendshipDto);
    }
}
