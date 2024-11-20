package cord.eoeo.momentwo.member.advice;

import cord.eoeo.momentwo.member.advice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler(NotFoundAuthorityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String notFoundAuthorityException() {
        return "권한이 없는 유저입니다.";
    }

    @ExceptionHandler(NotFoundAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String notFoundAccessException() {
        return "해당 앨범에 접근 권한이 없는 유저입니다.";
    }

    @ExceptionHandler(NotFoundGradeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundGradeException() {
        return "권한 입력이 올바르지 않습니다.";
    }

    @ExceptionHandler(NotChangeSelfException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String notChangeSelfException() {
        return "본인 스스로 권한을 변경할 수 없습니다.";
    }

    @ExceptionHandler(NotChangeSameAndUpGradeRulesException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String notChangeSameAndUpGradeRulesException() {
        return "본인과 크거나 같은 권한은 변경할 수 없습니다.";
    }

    @ExceptionHandler(NotChangeAdminException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String notChangeAdminException() {
        return "관리자가 아니기 때문에 변경할 수 없습니다.";
    }

    @ExceptionHandler(NotSelfKickException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String notSelfKickException() {
        return "본인을 추방하기 위해선 앨범 나가기를 해야합니다.";
    }

    @ExceptionHandler(AdminAlbumOutException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String adminAlbumOutException() {
        return "앨범에 멤버가 존재하기 때문에 관리자는 나갈 수 없습니다.\r\n나가길 원할 경우 관리자 권한을 넘겨주십시오.";
    }

    @ExceptionHandler(MemberNotInAlbumException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String memberNotInAlbumException() {
        return "앨범 멤버만 나갈 수 있습니다.";
    }
}
