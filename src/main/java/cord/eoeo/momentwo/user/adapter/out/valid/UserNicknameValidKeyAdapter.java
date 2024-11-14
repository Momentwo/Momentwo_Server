package cord.eoeo.momentwo.user.adapter.out.valid;

import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidKeyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserNicknameValidKeyAdapter implements UserNicknameValidKeyPort {

    @Override
    public String getKey(String nickname) {
        return "nickname/" + nickname;
    }
}
