package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.adapter.in.dto.*;
import cord.eoeo.momentwo.friendship.application.port.in.FriendshipUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipUseCase friendshipUseCase;

    // 요청
    @PostMapping("/friendship/request")
    @ResponseStatus(HttpStatus.OK)
    public void requestFriendship(@ModelAttribute @Valid RequestFriendshipDto requestFriendshipDto) {
        friendshipUseCase.requestFriendship(requestFriendshipDto);
    }

    // 친구요청에 대한 응답 (수락 & 거절)
    @PostMapping("/friendship/response")
    @ResponseStatus(HttpStatus.OK)
    public void responseFriendship(@RequestBody @Valid ResponseFriendshipDto responseFriendshipDto) {
        friendshipUseCase.responseFriendship(responseFriendshipDto);
    }

    // 친구 요청 취소
    @DeleteMapping("/friendship/request/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void requestFriendshipCancel(@ModelAttribute @Valid RequestFriendshipDto requestFriendshipDto) {
        friendshipUseCase.requestFriendshipCancel(requestFriendshipDto);
    }

    // 친구목록 조회
    @GetMapping("/friendship")
    @ResponseStatus(HttpStatus.OK)
    public FriendshipAllListResponseDto getFriendship() {
        return friendshipUseCase.getFriendship();
    }

    // 보낸 친구 요청 조회
    @GetMapping("/friendship/send")
    @ResponseStatus(HttpStatus.OK)
    public FriendshipSendListResponseDto getFriendshipSend() {
        return friendshipUseCase.getFriendshipSend();
    }

    // 받은 친구 요청 조회
    @GetMapping("/friendship/receive")
    @ResponseStatus(HttpStatus.OK)
    public FriendshipReceiveListResponseDto getFriendshipReceive() {
        return friendshipUseCase.getFriendshipReceive();
    }
}
