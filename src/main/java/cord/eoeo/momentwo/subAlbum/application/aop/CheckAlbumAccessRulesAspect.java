package cord.eoeo.momentwo.subAlbum.application.aop;

import cord.eoeo.momentwo.member.advice.exception.NotFoundAccessException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRulesCheck;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class CheckAlbumAccessRulesAspect {
    private final PhotoRulesCheck photoRulesCheck;
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;

    @Before("@annotation(cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules)")
    public void checkAlbumAccessRules(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] names = methodSignature.getParameterNames();
        Object[] values = joinPoint.getArgs();

        long albumId = 0;

        for(int i = 0; i < names.length; i++) {
            if(names[i].equals("albumId")) {
                albumId = (long) values[i];
                break;
            }
        }
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        if(albumId != 0 && !photoRulesCheck.isAlbumMember(albumId, user)) {
            throw new NotFoundAccessException();
        }
    }
}
