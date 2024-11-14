package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;
import cord.eoeo.momentwo.friendship.advice.exception.AlreadyFriendshipRequestException;
import cord.eoeo.momentwo.friendship.advice.exception.SelfRequestException;
import cord.eoeo.momentwo.friendship.application.port.in.RequestFriendshipUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.FriendsRequestPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.IsRequestFriendsPort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequestFriendshipService implements RequestFriendshipUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final IsRequestFriendsPort isRequestFriendsPort;
    private final FriendsRequestPort friendsRequestPort;

    // 친구요청
    @Override
    @Transactional
    public void requestFriendship(RequestFriendshipDto requestFriendshipDto) {
        User fromUser = userNicknameValidPort.authenticationValid();
        if(fromUser.getNickname().equals(requestFriendshipDto.getNickname())) {
            throw new SelfRequestException();
        }
        // 사용자 조회
        User toUser = userNicknameValidPort.userNicknameValid(requestFriendshipDto.getNickname());

        // 친구요청을 보냈었는지 확인
        if(!isRequestFriendsPort.isRequestFriends(fromUser, toUser)) {
            throw new AlreadyFriendshipRequestException();
        }

        // from -> to (true)
        // to -> from (false)
        friendsRequestPort.friendsRequest(fromUser, toUser, true);
        friendsRequestPort.friendsRequest(toUser, fromUser, false);
    }
}
