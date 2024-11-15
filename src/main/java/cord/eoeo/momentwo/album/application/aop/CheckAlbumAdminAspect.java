package cord.eoeo.momentwo.album.application.aop;

import cord.eoeo.momentwo.album.advice.exception.NotAlbumAdminException;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindGradeByAlbumIdAndUserIdRepo;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckAlbumAdminAspect {
    private final MemberFindGradeByAlbumIdAndUserIdRepo memberFindGradeByAlbumIdAndUserIdRepo;
    private final UserNicknameValidPort userNicknameValidPort;

    @Before("@annotation(cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin)")
    public void checkAlbumAdmin(JoinPoint joinPoint) throws Exception {
        Object[] values = joinPoint.getArgs();
        long albumId = 0;

        for(int i = 0; i < values.length; i++) {
            if(values[i] instanceof Long) {
                albumId = (long) values[i];
                break;
            }

            Field[] o = values[i].getClass().getDeclaredFields();
            for (Field f : o) {
                if (f.getName().equals("albumId")) {
                    f.setAccessible(true);
                    albumId = (long) f.get(values[i]);
                    break;
                }
            }
        }

        User user = userNicknameValidPort.authenticationValid();

        if(albumId != 0 &&
                !memberFindGradeByAlbumIdAndUserIdRepo.findMemberGradeByAlbumIdAndUserId(albumId, user.getId())
                        .getRules().equals(MemberAlbumGrade.ROLE_ALBUM_ADMIN))
        {
            throw new NotAlbumAdminException();
        }
    }
}
