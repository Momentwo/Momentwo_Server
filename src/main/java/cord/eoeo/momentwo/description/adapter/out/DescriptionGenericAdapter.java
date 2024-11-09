package cord.eoeo.momentwo.description.adapter.out;

import cord.eoeo.momentwo.description.application.port.out.DescriptionGenericJpaRepo;
import cord.eoeo.momentwo.description.application.port.out.DescriptionGenericRepo;
import cord.eoeo.momentwo.description.domain.Description;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DescriptionGenericAdapter implements DescriptionGenericRepo {
    private final DescriptionGenericJpaRepo descriptionGenericJpaRepo;

    @Override
    public void save(Description entity) {
        descriptionGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<Description> findById(Long id) {
        return descriptionGenericJpaRepo.findById(id);
    }

    @Override
    public List<Description> findAll() {
        return descriptionGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        descriptionGenericJpaRepo.deleteById(id);
    }
}
