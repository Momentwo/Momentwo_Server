package cord.eoeo.momentwo.config.security.filter;

import cord.eoeo.momentwo.config.security.jwt.TokenProvider;
import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private final String AUTHENTICATION_KEY = "Authorization";

    private final JWTBlackList jwtBlackList;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = resolveToken(request);
        if(StringUtils.hasText(jwtToken) && tokenProvider.validationToken(jwtToken)) {
            if(!jwtBlackList.isTokenBlackListed(jwtToken)) { // 블랙리스트(로그아웃) 토큰 확인
                Authentication authentication = tokenProvider.getAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 체인에 넣어주지 않으면 무조건 통과시킴(요청에서 200반환)
        filterChain.doFilter(request, response);
    }

    public String resolveToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(AUTHENTICATION_KEY);

        if(StringUtils.hasText(jwtToken) && jwtToken.startsWith("Bearer ")) {
            return jwtToken.substring(7);
        }
        return null;
    }
}
