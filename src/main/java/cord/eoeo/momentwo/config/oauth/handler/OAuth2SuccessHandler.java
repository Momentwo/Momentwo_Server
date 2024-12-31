package cord.eoeo.momentwo.config.oauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.config.security.jwt.TokenProvider;
import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.application.port.in.status.UserInfoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final String AUTHENTICATION_KEY = "Authorization";
    private final String REFRESH_KEY = "Refresh";
    private final TokenProvider tokenProvider;
    private final UserInfoUseCase userInfoUseCase;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        TokenResponseDto token = tokenProvider.createToken(authentication, "");
        // 바디 : 유저 정보 반환
        String body = objectMapper.writeValueAsString(userInfoUseCase.getUserInfo(oAuth2User.getName()));

        response.setStatus(HttpServletResponse.SC_OK); // 상태 코드
        response.setContentType("application/json"); // 컨텐트 타입
        response.setCharacterEncoding("UTF-8"); // 인코딩
        response.setHeader(AUTHENTICATION_KEY, token.getAccessToken()); // AT
        response.setHeader(REFRESH_KEY, token.getRefreshToken()); // RT
        response.getWriter().write(body); // 바디로 JSON 반환
    }
}
