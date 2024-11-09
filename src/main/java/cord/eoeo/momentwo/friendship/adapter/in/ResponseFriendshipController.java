package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.adapter.dto.ResponseFriendshipDto;
import cord.eoeo.momentwo.friendship.application.port.in.ResponseFriendshipUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ResponseFriendshipController {
    private final ResponseFriendshipUseCase responseFriendshipUseCase;

    // 친구요청에 대한 응답 (수락 & 거절)
    @PostMapping("/friendship/response")
    @ResponseStatus(HttpStatus.OK)
    public void responseFriendship(@RequestBody @Valid ResponseFriendshipDto responseFriendshipDto) {
        responseFriendshipUseCase.responseFriendship(responseFriendshipDto);
    }
}
