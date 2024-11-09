package cord.eoeo.momentwo.user.application.port.out.jpa;

import cord.eoeo.momentwo.user.application.port.out.UserGenericJpaRepo;

public interface UserExistsJpaRepo extends UserGenericJpaRepo {
    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);
}
