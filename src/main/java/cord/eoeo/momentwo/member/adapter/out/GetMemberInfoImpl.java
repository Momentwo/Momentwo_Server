package cord.eoeo.momentwo.member.adapter.out;

import cord.eoeo.momentwo.member.application.port.out.GetMemberInfo;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindUsernameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Configuration
@RequiredArgsConstructor
public class GetMemberInfoImpl implements GetMemberInfo {
    private final UserFindUsernameRepo userFindUsernameRepo;
    private final UserFindNicknameRepo userFindNicknameRepo;

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<User> getUserInfo(String username) {
        return CompletableFuture.supplyAsync(()
                -> userFindUsernameRepo.findByUsername(username).orElseThrow(NotFoundUserException::new));
    }

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<User> getUserInfoByNickname(String nickname) {
        return CompletableFuture.supplyAsync(()
                -> userFindNicknameRepo.findByNickname(nickname).orElseThrow(NotFoundUserException::new));
    }
}
