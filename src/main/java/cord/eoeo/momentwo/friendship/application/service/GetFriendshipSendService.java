package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipSendListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.in.GetFriendshipSendUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.GetFriendshipSendPort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetFriendshipSendService implements GetFriendshipSendUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final GetFriendshipSendPort getFriendshipSendPort;

    // 보낸 친구목록 조회
    @Override
    @Transactional(readOnly = true)
    public FriendshipSendListResponseDto getFriendshipSend() {
        User owner = userNicknameValidPort.authenticationValid();

        return getFriendshipSendPort.getFriendshipSend(owner);
    }
}
