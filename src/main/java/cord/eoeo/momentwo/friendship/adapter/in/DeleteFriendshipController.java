package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;
import cord.eoeo.momentwo.friendship.application.port.in.DeleteFriendsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DeleteFriendshipController {
    private final DeleteFriendsUseCase deleteFriendsUseCase;

    @DeleteMapping("/friendship/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFriends(@ModelAttribute @Valid RequestFriendshipDto requestFriendshipDto) {
        deleteFriendsUseCase.deleteFriends(requestFriendshipDto);
    }
}
