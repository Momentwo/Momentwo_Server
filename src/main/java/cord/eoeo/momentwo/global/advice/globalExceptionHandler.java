package cord.eoeo.momentwo.global.advice;

import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.advice.exception.PasswordMisMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.concurrent.CompletionException;

@RestControllerAdvice
public class globalExceptionHandler {
    // DTO에서 패턴에 따른 예외처리 적용
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String dtoValidation(final MethodArgumentNotValidException e) {
        return Objects.requireNonNull(e.getFieldError()).getDefaultMessage();
    }

    // Async 핸들러
    @ExceptionHandler(CompletionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void completionHandlerException(CompletionException ex) {
        Throwable cause = ex.getCause();
        if(cause instanceof NotFoundUserException) {
            throw new NotFoundUserException();
        }
        else {
            throw new PasswordMisMatchException();
        }
    }
}
