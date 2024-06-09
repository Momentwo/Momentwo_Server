package cord.eoeo.momentwo.user.application.port.out;

import java.util.concurrent.CompletableFuture;

public interface UserRegisterAsync {
    CompletableFuture<Void> checkUsernameDuplicate(String username);

    CompletableFuture<Void> checkUserNicknameDuplicate(String nickname);
}
