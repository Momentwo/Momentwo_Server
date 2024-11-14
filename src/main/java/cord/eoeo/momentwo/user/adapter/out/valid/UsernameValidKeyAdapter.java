package cord.eoeo.momentwo.user.adapter.out.valid;

import cord.eoeo.momentwo.user.application.port.out.valid.UsernameValidKeyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernameValidKeyAdapter implements UsernameValidKeyPort {

    @Override
    public String getKey(String username) {
        return "username/" + username;
    }
}
