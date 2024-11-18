package cord.eoeo.momentwo.elasticsearch.adpater.out.tag.manager;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.TagsElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.manager.AlbumTagsSavePort;
import cord.eoeo.momentwo.elasticsearch.domain.TagsDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlbumTagsSaveAdapter implements AlbumTagsSavePort {
    private final TagsElasticsearchRepo tagsElasticsearchRepo;

    @Override
    public void albumTagsSave(Album album, List<String> albumTags) {
        TagsDocument tagsDocument = new TagsDocument(album, albumTags);
        tagsElasticsearchRepo.save(tagsDocument);
    }
}
