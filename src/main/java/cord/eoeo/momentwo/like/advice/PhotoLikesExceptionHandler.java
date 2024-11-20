package cord.eoeo.momentwo.like.advice;

import cord.eoeo.momentwo.like.advice.exception.NotFoundPhotoLikesException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PhotoLikesExceptionHandler {
    @ExceptionHandler(NotFoundPhotoLikesException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundImageLikesException() {
        return "눌린 좋아요 없음";
    }
}
