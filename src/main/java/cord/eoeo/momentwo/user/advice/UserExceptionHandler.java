package cord.eoeo.momentwo.user.advice;

import cord.eoeo.momentwo.user.advice.exception.DuplicateNicknameException;
import cord.eoeo.momentwo.user.advice.exception.DuplicateUsernameException;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.advice.exception.PasswordMisMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundUserException() {
        return "유저가 존재하지 않습니다.";
    }

    @ExceptionHandler(PasswordMisMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String passwordMisMatchException() {
        return "비밀번호가 일치하지 않습니다.";
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String duplicateUsernameException() {
        return "이미 등록된 이메일입니다.";
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String duplicateNicknameException() {
        return "이미 등록된 별명입니다.";
    }
}
