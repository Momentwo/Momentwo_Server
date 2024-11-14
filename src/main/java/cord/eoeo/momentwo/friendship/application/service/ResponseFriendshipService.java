package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.adapter.dto.ResponseFriendshipDto;
import cord.eoeo.momentwo.friendship.application.port.in.ResponseFriendshipUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.ResponseProcessPort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResponseFriendshipService implements ResponseFriendshipUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final ResponseProcessPort responseProcessPort;

    // 친구요청 응답 (수락 & 거절)
    @Override
    @Transactional
    public void responseFriendship(ResponseFriendshipDto responseFriendshipDto) {
        User responseUser = userNicknameValidPort.authenticationValid();
        User requestUser = userNicknameValidPort.userNicknameValid(responseFriendshipDto.getNickname());

        // 수락 시 false -> true
        // 거절 시 data 삭제
        responseProcessPort.responseProcess(
                responseUser,
                requestUser,
                responseFriendshipDto.getAccept()
        );
    }
}
