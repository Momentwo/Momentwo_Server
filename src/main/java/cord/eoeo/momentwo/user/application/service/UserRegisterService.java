package cord.eoeo.momentwo.user.application.service;

import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;
import cord.eoeo.momentwo.user.application.port.in.UserRegisterUseCase;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.UserRegisterAsync;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserRegisterService implements UserRegisterUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRegisterAsync userRegisterAsync;

    @Transactional
    @Override
    public void register(UserRegisterRequestDto userRegisterRequestDto) {
        // 유저 이메일(Id) 중복체크
        CompletableFuture<Void> checkUsernameFuture = userRegisterAsync.checkUsernameDuplicate(userRegisterRequestDto.getUsername());

        // 유저 닉네임 중복 체크
        CompletableFuture<Void> checkNicknameFuture = userRegisterAsync.checkUserNicknameDuplicate(userRegisterRequestDto.getNickname());

        // 두 중복 체크가 정상적으로 통과한 경우
        // join() -> 작업이 정상적으로 동작했을 때 결과를 반환한다.
        // 예외 시 CompletionException 을 발생시킨다.
        CompletableFuture.allOf(checkUsernameFuture, checkNicknameFuture).join();
        User newUser = new User(
                userRegisterRequestDto.getName(),
                userRegisterRequestDto.getUsername(),
                passwordEncoder.encoder(userRegisterRequestDto.getPassword()),
                userRegisterRequestDto.getNickname(),
                userRegisterRequestDto.getBirthday(),
                userRegisterRequestDto.getPhone()
        );
        userRepository.save(newUser);
    }
}