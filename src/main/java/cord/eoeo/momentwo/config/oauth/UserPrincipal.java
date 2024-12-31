package cord.eoeo.momentwo.config.oauth;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.CustomUserDetailsDto;
import cord.eoeo.momentwo.user.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class UserPrincipal extends CustomUserDetailsDto implements OAuth2User {
    private final User user;
    private String primaryKey;
    private Map<String, Object> attributes;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        super(
                user.getNickname(),
                user.getPassword(),
                user.getUsername(),
                null
        );
        this.user = user;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoleUserGrade().toString()));
    }

    public UserPrincipal(User user, Map<String, Object> attributes, String primaryKey) {
        super(
                user.getNickname(),
                user.getPassword(),
                user.getUsername(),
                null
        );
        this.user = user;
        this.attributes = attributes;
        this.primaryKey = primaryKey;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoleUserGrade().toString()));
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
