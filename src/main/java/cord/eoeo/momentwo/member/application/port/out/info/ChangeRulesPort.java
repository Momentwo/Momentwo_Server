package cord.eoeo.momentwo.member.application.port.out.info;

import cord.eoeo.momentwo.user.domain.User;

public interface ChangeRulesPort {
    void changeRules(long albumId, User owner, User target, String rules);
}
