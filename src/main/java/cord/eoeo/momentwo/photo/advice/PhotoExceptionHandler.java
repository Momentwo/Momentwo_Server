package cord.eoeo.momentwo.photo.advice;

import cord.eoeo.momentwo.photo.advice.exception.*;
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notDeleteImageException() {
        return "삭제할 이미지 목록이 없습니다.";
    }

    @ExceptionHandler(NotFoundPhotoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundPhotoException() {
        return "사진이 존재하지 않습니다.";
    }

    @ExceptionHandler(PhotoCapacityFullException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String photoCapacityFullException() {
        return "앨범이 가득찼습니다.";
    }

    @ExceptionHandler(NotPhotoAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String notPhotoAccessException() {
        return "사진이 존재하지 않거나 사진에 접근할 권한이 없습니다.";
    }

    @ExceptionHandler(NotPhotoMoveException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String notPhotoMoveException() {
        return "이동할 수 없습니다.";
    }
}
