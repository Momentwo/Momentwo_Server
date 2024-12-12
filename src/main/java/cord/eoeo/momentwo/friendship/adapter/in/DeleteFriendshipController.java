package cord.eoeo.momentwo.friendship.adapter.in;

import cord.eoeo.momentwo.friendship.application.port.in.DeleteFriendsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeleteFriendshipController {
    private final DeleteFriendsUseCase deleteFriendsUseCase;

    @DeleteMapping("/friendship/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFriends(@PathVariable Long userId) {
        deleteFriendsUseCase.deleteFriends(userId);
    }
}
