package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.advice.exception.DuplicateNicknameException;
import cord.eoeo.momentwo.user.advice.exception.DuplicateUsernameException;
import cord.eoeo.momentwo.user.application.port.out.UserRegisterAsync;
import cord.eoeo.momentwo.user.application.port.out.jpa.UserExistsJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Configuration
public class UserRegisterAsyncAdapter implements UserRegisterAsync {
    private final UserExistsJpaRepo userExistsJpaRepo;

    @Override
    @Async("userTaskExecutor")
    public CompletableFuture<Void> checkUsernameDuplicate(String username) {
        return CompletableFuture.runAsync(() -> {
            if(userExistsJpaRepo.existsByUsername(username)) {
                throw new DuplicateUsernameException();
            }
        });
    }

    @Override
    @Async("userTaskExecutor")
    public CompletableFuture<Void> checkUserNicknameDuplicate(String nickname) {
        return CompletableFuture.runAsync(() -> {
            if(userExistsJpaRepo.existsByNickname(nickname)) {
                throw new DuplicateNicknameException();
            }
        });
    }
}
