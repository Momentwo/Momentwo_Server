package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipAllListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.in.GetFriendshipUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.GetAllFriendship;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetFriendshipService implements GetFriendshipUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final GetAllFriendship getAllFriendship;

    // 친구목록 조회
    @Override
    @Transactional(readOnly = true)
    public FriendshipAllListResponseDto getFriendship(int size, long cursor) {
        User owner = userNicknameValidPort.authenticationValid();

        return getAllFriendship.getAllFriendship(owner, PageRequest.of(0, size), cursor);
    }
}
