package cord.eoeo.momentwo.member.adapter.out.async;

import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByNicknamePort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class GetUserInfoByNicknameAdapter implements GetUserInfoByNicknamePort {
    private final UserNicknameValidPort userNicknameValidPort;

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<User> getUserInfoByNickname(String nickname) {
        return CompletableFuture.supplyAsync(()
                -> userNicknameValidPort.userNicknameValid(nickname)
        );
    }
}
