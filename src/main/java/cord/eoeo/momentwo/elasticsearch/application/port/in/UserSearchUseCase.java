package cord.eoeo.momentwo.elasticsearch.application.port.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.UserSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.UserSearchListResponseDto;

public interface UserSearchUseCase {
    UserSearchListResponseDto getUserElasticSearch(UserSearchRequestDto userSearchRequestDto);
}
