package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;
import cord.eoeo.momentwo.friendship.application.port.in.RequestFriendshipCancelUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.RequestCancelPort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequestFriendshipCancelService implements RequestFriendshipCancelUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final RequestCancelPort requestCancelPort;

    // 친구 요청 취소
    @Override
    @Transactional
    public void requestFriendshipCancel(RequestFriendshipDto requestFriendshipDto) {
        User fromUser = userNicknameValidPort.authenticationValid();
        // 사용자 조회
        User toUser = userNicknameValidPort.userNicknameValid(requestFriendshipDto.getNickname());

        requestCancelPort.requestCancel(fromUser, toUser);
    }
}
