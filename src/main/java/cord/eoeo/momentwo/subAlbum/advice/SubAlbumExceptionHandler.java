package cord.eoeo.momentwo.subAlbum.advice;

import cord.eoeo.momentwo.subAlbum.advice.exception.NotFoundSubAlbumException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SubAlbumExceptionHandler {
    @ExceptionHandler(NotFoundSubAlbumException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundSubAlbumException() {
        return "서브 앨범이 존재하지 않습니다.";
    }
}
