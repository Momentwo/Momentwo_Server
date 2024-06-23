package cord.eoeo.momentwo.album.advice;

import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlbumExceptionHandler {
    @ExceptionHandler(NotFoundAlbumException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundAlbumException() {
        return "앨범이 존재하지 않습니다.";
    }
}
