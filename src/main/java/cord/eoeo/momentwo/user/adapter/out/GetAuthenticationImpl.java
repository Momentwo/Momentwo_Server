package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetAuthenticationImpl implements GetAuthentication {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
