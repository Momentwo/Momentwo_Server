package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.advice.exception.DuplicateNicknameException;
import cord.eoeo.momentwo.user.advice.exception.DuplicateUsernameException;
import cord.eoeo.momentwo.user.application.port.out.UserRegisterAsync;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Configuration
public class UserRegisterAsyncImpl implements UserRegisterAsync {
    private final UserRepository userRepository;

    @Override
    @Async("userTaskExecutor")
    public CompletableFuture<Void> checkUsernameDuplicate(String username) {
        System.out.println("[username] :: " + Thread.currentThread().getName());
        return CompletableFuture.runAsync(() -> {
            if(userRepository.existsByUsername(username)) {
                throw new DuplicateUsernameException();
            }
        });
    }

    @Override
    @Async("userTaskExecutor")
    public CompletableFuture<Void> checkUserNicknameDuplicate(String nickname) {
        System.out.println("[nickname] :: " + Thread.currentThread().getName());
        return CompletableFuture.runAsync(() -> {
            if(userRepository.existsByNickname(nickname)) {
                throw new DuplicateNicknameException();
            }
        });
    }
}
