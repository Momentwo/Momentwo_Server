package cord.eoeo.momentwo.subAlbum.application.aop;

import cord.eoeo.momentwo.member.advice.exception.NotFoundAccessException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRulesCheck;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Aspect
@RequiredArgsConstructor
public class CheckAlbumAccessRulesAspect {
    private final PhotoRulesCheck photoRulesCheck;
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;

    @Before("@annotation(cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules)")
    public void checkAlbumAccessRules(JoinPoint joinPoint) throws Exception{
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
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        if(albumId != 0 && !photoRulesCheck.isAlbumMember(albumId, user)) {
            throw new NotFoundAccessException();
        }
    }
}
