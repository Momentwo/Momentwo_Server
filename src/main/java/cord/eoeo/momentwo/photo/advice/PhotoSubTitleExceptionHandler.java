package cord.eoeo.momentwo.photo.advice;

import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoSubTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PhotoSubTitleExceptionHandler {
    @ExceptionHandler(NotFoundPhotoSubTitleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundPhotoSubTitleException() {
        return "소제목이 존재하지 않습니다.";
    }
}
