package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordEncoderImpl implements PasswordEncoder {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public String encoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean matches(String input, String database) {
        return bCryptPasswordEncoder.matches(input, database);
    }
}
