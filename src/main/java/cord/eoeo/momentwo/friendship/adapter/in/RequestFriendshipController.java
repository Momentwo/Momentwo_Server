package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;
import cord.eoeo.momentwo.friendship.application.port.in.RequestFriendshipUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RequestFriendshipController {
    private final RequestFriendshipUseCase requestFriendshipUseCase;

    // 요청
    @PostMapping("/friendship/request")
    @ResponseStatus(HttpStatus.CREATED)
    public void requestFriendship(@ModelAttribute @Valid RequestFriendshipDto requestFriendshipDto) {
        requestFriendshipUseCase.requestFriendship(requestFriendshipDto);
    }
}
