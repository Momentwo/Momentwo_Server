package cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.photo.PhotoTagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhotoTagSearchPort {
    Page<PhotoTagsDocument> photoTagSearch(PhotoTagSearchRequestDto photoTagSearchRequestDto, Pageable pageable);
}
