package cord.eoeo.momentwo.subAlbum.advice;

import cord.eoeo.momentwo.subAlbum.advice.exception.NotDeleteSubAlbumException;
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

    @ExceptionHandler(NotDeleteSubAlbumException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notDeleteSubAlbumException() {
        return "삭제할 서브앨범이 없습니다.";
    }
}
