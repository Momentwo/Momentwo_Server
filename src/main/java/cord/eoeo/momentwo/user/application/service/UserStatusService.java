package cord.eoeo.momentwo.user.application.service;

import cord.eoeo.momentwo.config.security.jwt.TokenProvider;
import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.advice.exception.PasswordMisMatchException;
import cord.eoeo.momentwo.user.application.port.in.UserStatusUseCase;
import cord.eoeo.momentwo.user.application.port.out.AuthenticationManager;
import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserStatusService implements UserStatusUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final JWTBlackList jwtBlackList;

    @Transactional(readOnly = true)
    @Override
    public TokenResponseDto signIn(UserLoginRequestDto userLoginRequestDto) {
        // 아이디 확인
        User user = userRepository.findByUsername(userLoginRequestDto.getUsername()).orElseThrow(() -> {
            throw new NotFoundUserException();
        });
        // 비밀번호 확인
        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            throw new PasswordMisMatchException();
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginRequestDto.getUsername(),
                userLoginRequestDto.getPassword()
        );
        Authentication authentication = authenticationManager.getAuthentication(authenticationToken);

        return tokenProvider.createToken(authentication);
    }

    @Override
    public void blackListToken(String token) {
        jwtBlackList.blackListToken(token);
    }
}
