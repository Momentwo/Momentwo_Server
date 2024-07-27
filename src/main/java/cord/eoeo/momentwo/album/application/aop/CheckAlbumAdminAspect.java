package cord.eoeo.momentwo.album.application.aop;

import cord.eoeo.momentwo.album.advice.exception.NotAlbumAdminException;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckAlbumAdminAspect {

    @After("@annotation(cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin)")
    public void checkAlbumAdmin(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        Member member = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Member) {
                member = (Member) args[i];
            }
        }

        if(member != null && !member.getRules().equals(MemberAlbumGrade.ROLE_ALBUM_ADMIN)) {
            throw new NotAlbumAdminException();
        }
    }
}
