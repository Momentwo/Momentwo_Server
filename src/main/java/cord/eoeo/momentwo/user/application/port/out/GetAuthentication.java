package cord.eoeo.momentwo.user.application.port.out;

import org.springframework.security.core.Authentication;

public interface GetAuthentication {
    Authentication getAuthentication();
}
