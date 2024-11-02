package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.out.RefreshTokenGenericJpaRepo;
import cord.eoeo.momentwo.user.application.port.out.RefreshTokenGenericRepo;
import cord.eoeo.momentwo.user.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class RefreshTokenGenericRepoAdapter implements RefreshTokenGenericRepo {
    private final RefreshTokenGenericJpaRepo refreshTokenGenericJpaRepo;

    @Override
    public void save(RefreshToken entity) {
        refreshTokenGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<RefreshToken> findById(Long aLong) {
        return refreshTokenGenericJpaRepo.findById(aLong);
    }

    @Override
    public List<RefreshToken> findAll() {
        return refreshTokenGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        refreshTokenGenericJpaRepo.deleteById(aLong);
    }
}
