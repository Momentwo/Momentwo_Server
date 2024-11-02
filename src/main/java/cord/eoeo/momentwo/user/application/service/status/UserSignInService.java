package cord.eoeo.momentwo.user.application.service.status;

import cord.eoeo.momentwo.config.security.jwt.TokenProvider;
import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.advice.exception.PasswordMisMatchException;
import cord.eoeo.momentwo.user.application.port.in.status.UserSignInUseCase;
import cord.eoeo.momentwo.user.application.port.out.AuthenticationManager;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindUsernameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletionException;

@Service
@RequiredArgsConstructor
public class UserSignInService implements UserSignInUseCase {
    private final UserFindUsernameRepo userFindUsernameRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public TokenResponseDto signIn(UserLoginRequestDto userLoginRequestDto) {
        // 아이디 확인
        User user = userFindUsernameRepo.findByUsername(userLoginRequestDto.getUsername()).orElseThrow(
                () -> new CompletionException(new NotFoundUserException()));
        // 비밀번호 확인
        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            throw new CompletionException(new PasswordMisMatchException());
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginRequestDto.getUsername(),
                userLoginRequestDto.getPassword()
        );
        Authentication authentication = authenticationManager.getAuthentication(authenticationToken);

        return tokenProvider.createToken(authentication, "");
    }
}
