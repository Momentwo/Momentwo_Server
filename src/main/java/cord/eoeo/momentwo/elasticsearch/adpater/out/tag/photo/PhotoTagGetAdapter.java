package cord.eoeo.momentwo.elasticsearch.adpater.out.tag.photo;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.photo.PhotoTagGetResponseDto;
import cord.eoeo.momentwo.elasticsearch.advice.tag.exception.NotFoundPhotoTagInfoException;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagGetPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoTagGetAdapter implements PhotoTagGetPort {
    private final PhotoTagElasticsearchRepo photoTagElasticsearchRepo;

    @Override
    public PhotoTagGetResponseDto photoTagGet(long photoId) {
        String id = "tags_" + photoId;

        return new PhotoTagGetResponseDto()
                .toDo(photoTagElasticsearchRepo.findById(id).orElseThrow(NotFoundPhotoTagInfoException::new));
    }
}
