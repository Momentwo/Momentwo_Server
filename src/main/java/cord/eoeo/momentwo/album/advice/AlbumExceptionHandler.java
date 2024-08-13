package cord.eoeo.momentwo.album.advice;

import cord.eoeo.momentwo.album.advice.exception.NotAlbumAdminException;
import cord.eoeo.momentwo.album.advice.exception.NotCreateAlbumException;
import cord.eoeo.momentwo.album.advice.exception.NotDeleteAlbumException;
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

    @ExceptionHandler(NotAlbumAdminException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notAlbumAdminException() {
        return "앨범 관리자만 해당 기능을 사용할 수 있습니다.";
    }

    @ExceptionHandler(NotDeleteAlbumException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notDeleteAlbumException() {
        return "멤버가 존재하는 앨범은 삭제할 수 없습니다.";
    }

    @ExceptionHandler(NotCreateAlbumException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notCreateAlbumException() {
        return "앨범이 20개 이기 때문에 앨범을 만들 수 없습니다.";
    }
}
