package cord.eoeo.momentwo.image.advice;

import cord.eoeo.momentwo.image.advice.exception.ImageDownloadFailException;
import cord.eoeo.momentwo.image.advice.exception.NotFoundFileImageException;
import cord.eoeo.momentwo.image.advice.exception.NotFoundImageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ImageExceptionHandler {
    @ExceptionHandler(NotFoundImageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundImageException() {
        return "이미지를 찾을 수 없습니다.";
    }

    @ExceptionHandler(ImageDownloadFailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String imageDownloadFailException() {
        return "이미지 다운로드 실패";
    }

    @ExceptionHandler(NotFoundFileImageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundFileImageException() {
        return "파일에 이미지가 존재하지 않습니다.";
    }
}
