package cord.eoeo.momentwo.config.security.jwt;

import cord.eoeo.momentwo.config.security.filter.JWTFilter;
import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JWTSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;
    private final JWTBlackList jwtBlackList;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(new JWTFilter(tokenProvider, jwtBlackList),
                UsernamePasswordAuthenticationFilter.class);
    }
}
