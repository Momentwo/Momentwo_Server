package cord.eoeo.momentwo.elasticsearch.adpater.out.date;

import cord.eoeo.momentwo.elasticsearch.application.port.out.date.DateElasticsearchErRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.date.DateElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.domain.DateDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DateElasticsearchAdapter implements DateElasticsearchRepo {
    private final DateElasticsearchErRepo dateElasticsearchErRepo;

    @Override
    public void save(DateDocument entity) {
        dateElasticsearchErRepo.save(entity);
    }

    @Override
    public Optional<DateDocument> findById(String id) {
        return dateElasticsearchErRepo.findById(id);
    }

    @Override
    public void deleteById(String id) {
        dateElasticsearchErRepo.deleteById(id);
    }
}
