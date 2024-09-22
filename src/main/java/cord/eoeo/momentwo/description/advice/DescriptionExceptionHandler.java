package cord.eoeo.momentwo.description.advice;

import cord.eoeo.momentwo.description.advice.exception.NotFoundDescriptionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DescriptionExceptionHandler {
    @ExceptionHandler(NotFoundDescriptionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundDescriptionException() {
        return "작성 없음";
    }
}
