package cord.eoeo.momentwo.config.security.jwt;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.CustomUserDetailsDto;
import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.application.port.out.RefreshTokenRepository;
import cord.eoeo.momentwo.user.domain.RefreshToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Data
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
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] secretKey = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(secretKey);
    }

    // 생성자 초기화
    public TokenProvider(@Value("${jwt.tokenValidationTime}") long tokenValidationTime,
                         @Value("${jwt.secret}") String secret, RefreshTokenRepository refreshTokenRepository,
                         UserDetailsService userDetailsService) {
        this.secret = secret;
        this.tokenValidationTime = tokenValidationTime * TOKEN_PRODUCT_TIME;
        this.refreshTokenValidationTime = tokenValidationTime * REFRESH_TOKEN_PRODUCT_TIME;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userDetailsService = userDetailsService;
    }

    // 토큰 생성
    public TokenResponseDto createToken(Authentication authentication, String refresh) {
        // customUserDetails 를 사용하기 위해 선언 (jwt 패키지 확인)
        CustomUserDetailsDto userDetails = (CustomUserDetailsDto) authentication.getPrincipal();

        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        long now = (new Date()).getTime();

        Date expirationTime = new Date(now + tokenValidationTime);
        Date refreshTokenExpirationTime = new Date(now + refreshTokenValidationTime);

        String accessToken = Jwts.builder()
                .setSubject(userDetails.getNickname())
                .setExpiration(expirationTime)
                .claim(AUTHENTICATION_KEY, authorities)
                .claim(TOKEN_VALID_TIME, expirationTime)
                .claim(REFRESH_TOKEN_VALID_TIME, refreshTokenExpirationTime)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(refreshTokenExpirationTime)
                .claim(REFRESH_TOKEN_VALID_TIME, refreshTokenExpirationTime)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        saveOrEditRefreshToken(
                refresh, // 이전의 리프레시 토큰
                refreshToken, // 갱신할 리프레시 토큰
                authentication.getName(),
                refreshTokenExpirationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );

        return new TokenResponseDto().toDo(accessToken, refreshToken);
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

    public void saveOrEditRefreshToken(String refresh, String newRefresh, String nickname, LocalDate expiration) {
        if(refresh.isEmpty()) {
            RefreshToken saveRefreshToken = new RefreshToken(newRefresh, nickname, expiration);
            refreshTokenRepository.save(saveRefreshToken);
            return;
        }
        RefreshToken editRefreshToken = refreshTokenRepository.findByRefreshToken(refresh).orElseThrow();
        editRefreshToken.setToken(newRefresh);
        editRefreshToken.setExpirationDate(expiration);
        refreshTokenRepository.save(editRefreshToken);
    }

    public boolean validRefreshToken(String token) {
        return refreshTokenRepository.findByRefreshToken(token)
                .filter(refreshToken -> refreshToken.getExpirationDate().isAfter(LocalDate.now()))
                .isPresent();
    }

    public String getUsernameFromRefreshToken(String refreshToken) {
        return Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody()
                .getSubject();
    }

    public TokenResponseDto reissueRefreshToken(String refreshToken) {
        // 리프레시 토큰 만료 여부 확인
        if(!validRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("토큰이 만료되어 재로그인 해야합니다.");
        }
        // 사용자 정보 추출
        String username = getUsernameFromRefreshToken(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        // 추출한 정보를 토대로 토큰 재발급
        return createToken(authentication, refreshToken);
    }
}
