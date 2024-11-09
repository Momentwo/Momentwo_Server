package cord.eoeo.momentwo.photo.adapter.out;

import cord.eoeo.momentwo.member.advice.exception.NotFoundAuthorityException;
import cord.eoeo.momentwo.photo.application.port.out.MoveCheckAdminOrSelfPort;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.RoleUserGrade;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoveCheckAdminOrSelfAdapter implements MoveCheckAdminOrSelfPort {

    @Override
    public void moveCheckAdminOrSelf(Photo photo, User user) {
        if(
            photo.getUser().equals(user) ||
            user.getRoleUserGrade().equals(RoleUserGrade.ROLE_ADMIN) ||
            user.getRoleUserGrade().equals(RoleUserGrade.ROLE_MANAGER)
        ) {
            return;
        }
        throw new NotFoundAuthorityException();
    }
}
