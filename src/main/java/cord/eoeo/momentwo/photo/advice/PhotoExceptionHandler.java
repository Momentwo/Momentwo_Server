package cord.eoeo.momentwo.photo.advice;

import cord.eoeo.momentwo.photo.advice.exception.NotDeleteImageException;
import cord.eoeo.momentwo.photo.advice.exception.PhotoUploadFailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PhotoExceptionHandler {
    @ExceptionHandler(PhotoUploadFailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String photoUploadFailException() {
        return "이미지 업로드 실패";
    }

    @ExceptionHandler(NotDeleteImageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notDeleteImageException() {
        return "삭제할 이미지 목록이 없습니다.";
    }
}
