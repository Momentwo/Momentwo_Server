package cord.eoeo.momentwo.config.security.jwt;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider implements InitializingBean {
    private final String AUTHENTICATION_KEY = "auth";
    private final String TOKEN_VALID_TIME = "tokenValid";
    private final String REFRESH_TOKEN_VALID_TIME = "refreshToken";
    private final int TOKEN_PRODUCT_TIME = 500;
    private final int REFRESH_TOKEN_PRODUCT_TIME = 24 * 7 * 1000;
    private Key key;
    private String secret;
    private Long tokenValidationTime;
    private Long refreshTokenValidationTime;
    private String refreshToken;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] secretKey = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(secretKey);
    }

    // 생성자 초기화
    public TokenProvider(@Value("${jwt.tokenValidationTime}") long tokenValidationTime,
                         @Value("${jwt.secret}") String secret) {
        this.secret = secret;
        this.tokenValidationTime = tokenValidationTime * TOKEN_PRODUCT_TIME;
        this.refreshTokenValidationTime = tokenValidationTime * REFRESH_TOKEN_PRODUCT_TIME;
    }

    // 토큰 생성
    public TokenResponseDto createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        long now = (new Date()).getTime();

        Date expirationTime = new Date(now + tokenValidationTime);
        Date refreshTokenExpirationTime = new Date(now + refreshTokenValidationTime);

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(expirationTime)
                .claim(AUTHENTICATION_KEY, authorities)
                .claim(TOKEN_VALID_TIME, expirationTime)
                .claim(REFRESH_TOKEN_VALID_TIME, refreshTokenExpirationTime)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpirationTime)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenResponseDto().toDo(accessToken, refreshToken);
    }

    // 리프레시 토큰 정보
    public String getRefreshToken() {
        return refreshToken;
    }

    // 토큰 정보를 통해 Authentication 객체 생성
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<SimpleGrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHENTICATION_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validationToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            return true;
        } catch(SecurityException | MalformedJwtException e) {
            log.info("잘못된 토큰입니다.");
        } catch(ExpiredJwtException e) {
            log.info("이미 만료된 토큰입니다.");
        } catch(UnsupportedJwtException e) {
            log.info("지원하지 않는 토큰입니다.");
        } catch(IllegalArgumentException e) {
            log.info("토큰이 잘못되었습니다.");
        }
        return false;
    }
}
