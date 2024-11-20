package cord.eoeo.momentwo.tag.advice;

import cord.eoeo.momentwo.tag.advice.exception.TagDuplicateException;
import cord.eoeo.momentwo.tag.advice.exception.NotFoundTagException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TagExceptionHandler {
    @ExceptionHandler(TagDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String tagDuplicateException() {
        return "중복된 태그가 존재합니다";
    }

    @ExceptionHandler(NotFoundTagException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundTagException() {
        return "태그가 존재하지 않습니다.";
    }
}
