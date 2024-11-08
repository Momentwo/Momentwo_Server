package cord.eoeo.momentwo.friendship.application.service;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;
import cord.eoeo.momentwo.friendship.application.port.in.RequestFriendshipCancelUseCase;
import cord.eoeo.momentwo.friendship.application.port.out.process.RequestCancelPort;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequestFriendshipCancelService implements RequestFriendshipCancelUseCase {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final RequestCancelPort requestCancelPort;

    // 친구 요청 취소
    @Override
    @Transactional
    public void requestFriendshipCancel(RequestFriendshipDto requestFriendshipDto) {
        User fromUser = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName()).orElseThrow(
                NotFoundUserException::new
        );
        // 사용자 조회
        User toUser = userFindNicknameRepo.findByNickname(requestFriendshipDto.getNickname()).orElseThrow(
                NotFoundUserException::new
        );

        requestCancelPort.requestCancel(fromUser, toUser);
    }
}
