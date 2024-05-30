package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.out.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationManagerImpl implements AuthenticationManager {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Override
    public Authentication getAuthentication(UsernamePasswordAuthenticationToken authenticationToken) {
        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }
}
