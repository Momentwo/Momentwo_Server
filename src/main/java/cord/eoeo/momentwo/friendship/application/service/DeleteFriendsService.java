package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.application.port.in.DeleteFriendsUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.DeleteFriendsPort;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteFriendsService implements DeleteFriendsUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final UserGenericRepo userGenericRepo;
    private final DeleteFriendsPort deleteFriendsPort;

    // 친구 삭제
    @Override
    @Transactional
    public void deleteFriends(Long userId) {
        User toUser = userNicknameValidPort.authenticationValid();
        User fromUser = userGenericRepo.findById(userId).orElseThrow(NotFoundUserException::new);

        deleteFriendsPort.deleteFriends(toUser, fromUser);
    }
}
