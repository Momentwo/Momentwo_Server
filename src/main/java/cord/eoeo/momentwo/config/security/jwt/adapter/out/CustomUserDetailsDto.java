package cord.eoeo.momentwo.config.security.jwt.adapter.out;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class CustomUserDetailsDto implements UserDetails {
    private final String nickname;
    private final String password;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetailsDto(
            String nickname, String password, String username, Collection<? extends GrantedAuthority> authorities) {
        this.nickname = nickname;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
