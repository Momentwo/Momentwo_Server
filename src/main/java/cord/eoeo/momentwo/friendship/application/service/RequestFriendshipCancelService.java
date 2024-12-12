package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.application.port.in.RequestFriendshipCancelUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.RequestCancelPort;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequestFriendshipCancelService implements RequestFriendshipCancelUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final UserGenericRepo userGenericRepo;
    private final RequestCancelPort requestCancelPort;

    // 친구 요청 취소
    @Override
    @Transactional
    public void requestFriendshipCancel(Long userId) {
        User fromUser = userNicknameValidPort.authenticationValid();
        // 사용자 조회
        User toUser = userGenericRepo.findById(userId).orElseThrow(NotFoundUserException::new);

        requestCancelPort.requestCancel(fromUser, toUser);
    }
}
