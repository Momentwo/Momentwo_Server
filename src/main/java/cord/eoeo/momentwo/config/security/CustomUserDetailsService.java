package cord.eoeo.momentwo.config.security;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.CustomUserDetailsDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(NotFoundUserException::new);
    }

    public UserDetails createUserDetails(User user) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRoleUserGrade().toString());

        // 커스텀으로 변경해서 담음
        return new CustomUserDetailsDto(
                user.getNickname(),
                user.getPassword(),
                user.getUsername(),
                Collections.singleton(simpleGrantedAuthority)
        );
        // 여기 유저 패스워드 토큰으로 교체해보자
//        return new org.springframework.security.core.userdetails.User(
//                user.getNickname(),
//                user.getPassword(),
//                Collections.singleton(simpleGrantedAuthority));
    }
}
