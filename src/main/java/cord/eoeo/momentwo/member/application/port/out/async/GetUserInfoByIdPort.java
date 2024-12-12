package cord.eoeo.momentwo.member.application.port.out.async;

import cord.eoeo.momentwo.user.domain.User;

import java.util.concurrent.CompletableFuture;

public interface GetUserInfoByIdPort {
    CompletableFuture<User> getUserInfoById(Long id);
}
