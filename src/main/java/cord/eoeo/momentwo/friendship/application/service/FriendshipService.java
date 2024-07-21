package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.adapter.in.dto.RequestFriendshipDto;
import cord.eoeo.momentwo.friendship.adapter.in.dto.ResponseFriendshipDto;
import cord.eoeo.momentwo.friendship.advice.exception.AlreadyFriendshipRequestException;
import cord.eoeo.momentwo.friendship.application.port.in.FriendshipUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipProcess;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendshipService implements FriendshipUseCase {
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;
    private final FriendshipProcess friendshipProcess;

    // 친구요청
    @Override
    @Transactional
    public void requestFriendship(RequestFriendshipDto requestFriendshipDto) {
        User fromUser = userRepository.findByNickname(getAuthentication.getAuthentication().getName()).orElseThrow(
                NotFoundUserException::new
        );
        // 사용자 조회
        User toUser = userRepository.findByNickname(requestFriendshipDto.getNickname()).orElseThrow(
                NotFoundUserException::new
        );

        // 친구요청을 보냈었는지 확인
        if(!friendshipProcess.isRequestFriends(fromUser, toUser)) {
            throw new AlreadyFriendshipRequestException();
        }

        // from -> to (true)
        // to -> from (false)
        friendshipProcess.friendsRequest(fromUser, toUser, true);
        friendshipProcess.friendsRequest(toUser, fromUser, false);
    }

    // 친구요청 응답 (수락 & 거절)
    @Override
    @Transactional
    public void responseFriendship(ResponseFriendshipDto responseFriendshipDto) {
        User responseUser = userRepository.findByNickname(getAuthentication.getAuthentication().getName()).orElseThrow(
                NotFoundUserException::new
        );
        User requestUser = userRepository.findByNickname(responseFriendshipDto.getNickname()).orElseThrow(
                NotFoundUserException::new
        );

        // 수락 시 false -> true
        // 거절 시 data 삭제
        friendshipProcess.responseProcess(
                responseUser,
                requestUser,
                responseFriendshipDto.getAccept()
        );
    }
}
