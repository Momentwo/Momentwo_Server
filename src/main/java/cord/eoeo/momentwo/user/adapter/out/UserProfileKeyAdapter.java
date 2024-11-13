package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.in.profile.UserProfileKeyPort;
import org.springframework.stereotype.Component;

@Component
public class UserProfileKeyAdapter implements UserProfileKeyPort {

    @Override
    public String getKey(String nickname) {
        return "userprofile/" + nickname;
    }
}
