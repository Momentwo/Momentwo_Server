package cord.eoeo.momentwo.description.adapter.out;

import cord.eoeo.momentwo.description.application.port.out.DescriptionJpaRepository;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRepository;
import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DescriptionRepositoryImpl implements DescriptionRepository {
    private final DescriptionJpaRepository descriptionJpaRepository;

    @Override
    public void save(Description description) {
        descriptionJpaRepository.save(description);
    }

    @Override
    public void deleteByPhoto(Photo photo) {
        descriptionJpaRepository.deleteByPhoto(photo);
    }

    @Override
    public Optional<Description> findByPhoto(Photo photo) {
        return descriptionJpaRepository.findByPhoto(photo);
    }
}
