package cord.eoeo.momentwo.member.adapter.out.async;

import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByIdPort;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class GetUserInfoByIdAdapter implements GetUserInfoByIdPort {
    private final UserGenericRepo userGenericRepo;

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<User> getUserInfoById(Long id) {
        return CompletableFuture.supplyAsync(()
            -> userGenericRepo.findById(id).orElseThrow(NotFoundUserException::new)
        );
    }
}
