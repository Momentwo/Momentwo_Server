package cord.eoeo.momentwo.member.application.port.out;

import cord.eoeo.momentwo.user.domain.User;

import java.util.concurrent.CompletableFuture;

public interface GetMemberInfo {
    CompletableFuture<User> getUserInfo(String username);
    CompletableFuture<User> getUserInfoByNickname(String nickname);
}
