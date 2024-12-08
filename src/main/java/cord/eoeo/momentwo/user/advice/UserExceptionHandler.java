package cord.eoeo.momentwo.user.advice;

import cord.eoeo.momentwo.user.advice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundUserException() {
        return "유저가 존재하지 않습니다.";
    }

    @ExceptionHandler(PasswordMisMatchException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String passwordMisMatchException() {
        return "비밀번호가 일치하지 않습니다.";
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String duplicateUsernameException() {
        return "이미 등록된 이메일입니다.";
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String duplicateNicknameException() {
        return "이미 등록된 별명입니다.";
    }

    @ExceptionHandler(NotInviteUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notInviteUserException() {
        return "친구 초대 목록에 존재하지 않는 유저가 포함되어 있습니다.";
    }

    @ExceptionHandler(RefreshTokenValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String refreshTokenValidException() {
        return "리프레시 토큰이 만료되었습니다.";
    }

    @ExceptionHandler(NotFoundOAuthInfoTypeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String notFoundOAuthInfoTypeException() {
        return "지원하지 않는 OAuth2 타입입니다.";
    }
}
