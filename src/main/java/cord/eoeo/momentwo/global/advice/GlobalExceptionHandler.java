package cord.eoeo.momentwo.global.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // DTO에서 패턴에 따른 예외처리 적용
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String dtoValidation(final MethodArgumentNotValidException e) {
        return Objects.requireNonNull(e.getFieldError()).getDefaultMessage();
    }
}
