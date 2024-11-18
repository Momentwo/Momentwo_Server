package cord.eoeo.momentwo.elasticsearch.adpater.out.tag;

import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.TagsElasticsearchErRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.TagsElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.domain.TagsDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TagsElasticsearchAdapter implements TagsElasticsearchRepo {
    private final TagsElasticsearchErRepo tagsElasticsearchErRepo;

    @Override
    public void save(TagsDocument entity) {
        tagsElasticsearchErRepo.save(entity);
    }

    @Override
    public Optional<TagsDocument> findById(String id) {
        return tagsElasticsearchErRepo.findById(id);
    }

    @Override
    public void deleteById(String id) {
        tagsElasticsearchErRepo.deleteById(id);
    }
}
