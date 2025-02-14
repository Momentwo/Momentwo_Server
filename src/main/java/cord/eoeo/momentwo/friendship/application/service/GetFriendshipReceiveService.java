package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipReceiveListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.in.GetFriendshipReceiveUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.GetFriendshipReceivePort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetFriendshipReceiveService implements GetFriendshipReceiveUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final GetFriendshipReceivePort getFriendshipReceivePort;

    // 받은 친구목록 조회
    @Override
    @Transactional(readOnly = true)
    public FriendshipReceiveListResponseDto getFriendshipReceive() {
        User owner = userNicknameValidPort.authenticationValid();

        return getFriendshipReceivePort.getFriendshipReceive(owner);
    }
}
