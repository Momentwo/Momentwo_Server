package cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.photo.PhotoTagGetResponseDto;

public interface PhotoTagGetPort {
    PhotoTagGetResponseDto photoTagGet(long photoId);
}
