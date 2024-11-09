package cord.eoeo.momentwo.user.application.port.out.jpa;

import cord.eoeo.momentwo.user.application.port.out.UserGenericJpaRepo;
import cord.eoeo.momentwo.user.domain.User;

public interface UserDeleteJpaRepo extends UserGenericJpaRepo {
    void delete(User user);
}
