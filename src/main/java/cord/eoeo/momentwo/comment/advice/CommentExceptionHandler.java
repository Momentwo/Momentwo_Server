package cord.eoeo.momentwo.comment.advice;

import cord.eoeo.momentwo.comment.advice.exception.NotCommentAccessException;
import cord.eoeo.momentwo.comment.advice.exception.NotFoundCommentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentExceptionHandler {
    @ExceptionHandler(NotFoundCommentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundCommentException() {
        return "존재하지 않는 댓글입니다.";
    }

    @ExceptionHandler(NotCommentAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String notCommentAccessException() {
        return "댓글에 접근할 권한이 없습니다.";
    }
}
