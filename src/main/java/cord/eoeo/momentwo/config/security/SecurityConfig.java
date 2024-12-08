package cord.eoeo.momentwo.config.security;

import cord.eoeo.momentwo.config.oauth.CustomAuthorizationCodeTokenResponseClient;
import cord.eoeo.momentwo.config.oauth.CustomOAuth2UserService;
import cord.eoeo.momentwo.config.oauth.handler.OAuth2FailureHandler;
import cord.eoeo.momentwo.config.oauth.handler.OAuth2SuccessHandler;
import cord.eoeo.momentwo.config.security.handler.AuthenticationEntryPointHandler;
import cord.eoeo.momentwo.config.security.handler.CustomLogoutSuccessHandler;
import cord.eoeo.momentwo.config.security.handler.JWTAccessDeniedHandler;
import cord.eoeo.momentwo.config.security.jwt.JWTSecurityConfig;
import cord.eoeo.momentwo.config.security.jwt.TokenProvider;
import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JWTBlackList jwtBlackList;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    // OAuth2
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailureHandler oAuth2FailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    // 카카오 필터
    private final CustomAuthorizationCodeTokenResponseClient customAuthorizationCodeTokenResponseClient;

    private static final String[] WHITE_LIST = {
            "/signup",
            "/signin",
            "/check_email",
            "/check_nickname",
            "/search/username",
            "/temp/password",
            "/reissue"
    };

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPointHandler)

                .and()
                .logout()
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // OAuth 로그인
                .and()
                .oauth2Login()
                .successHandler(oAuth2SuccessHandler)
                .failureHandler(oAuth2FailureHandler)
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint // 카카오 인증 시 사용
                        .accessTokenResponseClient(customAuthorizationCodeTokenResponseClient))
                .userInfoEndpoint(endPoint -> endPoint.userService(customOAuth2UserService))

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, WHITE_LIST).permitAll()
                .antMatchers("/**").access("hasRole(\"ROLE_COMMON_MEMBER\")")
                .anyRequest().authenticated()

                .and()
                .apply(new JWTSecurityConfig(tokenProvider, jwtBlackList));

        return httpSecurity.build();
    }
}
