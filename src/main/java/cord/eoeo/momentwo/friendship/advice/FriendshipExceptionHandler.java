package cord.eoeo.momentwo.friendship.advice;

import cord.eoeo.momentwo.friendship.advice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FriendshipExceptionHandler {
    @ExceptionHandler(NotFoundFriendshipRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundFriendshipRequestException() {
        return "요청했던 정보를 찾을 수 없습니다.";
    }

    @ExceptionHandler(AlreadyFriendshipException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String alreadyFriendshipException() {
        return "이미 해당 유저와 친구입니다.";
    }

    @ExceptionHandler(AlreadyFriendshipRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String alreadyFriendshipRequestException() {
        return "이미 친구요청이 되어 있습니다.";
    }

    @ExceptionHandler(SelfRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String selfInviteException() {
        return "스스로에게 요청할 수 없습니다.";
    }

    @ExceptionHandler(NotDeleteFriendsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notDeleteFriendsException() {
        return "친구가 되어 있어야 삭제가 가능합니다.";
    }
}
