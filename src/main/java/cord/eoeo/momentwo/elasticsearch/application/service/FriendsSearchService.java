package cord.eoeo.momentwo.elasticsearch.application.service;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.UserSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.FriendsSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.out.FriendsElasticSearchManager;
import cord.eoeo.momentwo.elasticsearch.application.port.in.FriendsSearchUseCase;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendsSearchService implements FriendsSearchUseCase {
    private final FriendsElasticSearchManager friendsElasticSearchManager;
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;

    public void save(long id, String toNickname) {
        friendsElasticSearchManager.save(id, toNickname);
    }

    public FriendsSearchListResponseDto getFriends(UserSearchRequestDto userSearchRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        Pageable pageable = PageRequest.of(userSearchRequestDto.getPage(), userSearchRequestDto.getSize());

        return new FriendsSearchListResponseDto()
                .toDo(friendsElasticSearchManager
                        .getFriendsPaging(userSearchRequestDto.getNickname(), user, pageable));
    }
}
