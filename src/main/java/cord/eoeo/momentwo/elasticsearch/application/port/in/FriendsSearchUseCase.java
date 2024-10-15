package cord.eoeo.momentwo.elasticsearch.application.port.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.UserSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.FriendsSearchListResponseDto;

public interface FriendsSearchUseCase {
    FriendsSearchListResponseDto getFriends(UserSearchRequestDto userSearchRequestDto);
}
