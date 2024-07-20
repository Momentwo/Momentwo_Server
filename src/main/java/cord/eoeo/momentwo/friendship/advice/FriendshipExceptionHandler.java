package cord.eoeo.momentwo.friendship.advice;

import cord.eoeo.momentwo.friendship.advice.exception.NotFoundFriendshipRequestException;
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
}
