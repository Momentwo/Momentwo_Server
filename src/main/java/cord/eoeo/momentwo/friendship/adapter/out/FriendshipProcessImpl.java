package cord.eoeo.momentwo.friendship.adapter.out;

import cord.eoeo.momentwo.friendship.adapter.in.dto.*;
import cord.eoeo.momentwo.friendship.advice.exception.AlreadyFriendshipException;
import cord.eoeo.momentwo.friendship.advice.exception.NotFoundFriendshipRequestException;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipProcess;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipRepository;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipProcessImpl implements FriendshipProcess {
    private final FriendshipRepository friendshipRepository;

    @Override
    @Transactional
    public void friendsRequest(User from, User to, boolean type) {
        Friendship newFriendship = new Friendship(
                from,
                to,
                type
        );
        friendshipRepository.save(newFriendship);
    }

    @Override
    @Transactional
    public void responseProcess(User responseUser, User requestUser, Boolean accept) {
        Friendship friendship = friendshipRepository.findByToUserAndFromUserAndAccept(
                responseUser,
                requestUser,
                false
        ).orElseThrow(NotFoundFriendshipRequestException::new);

        if(!accept) {
            deleteFriendship(responseUser, requestUser);
            return;
        }

        friendship.setAccept(true);
        friendshipRepository.save(friendship);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isRequestFriends(User fromUser, User toUser) {
        // 요청 받은 사람의 accept 이 false 인지 확인
        Friendship friendship = friendshipRepository.findByToUserAndFromUser(toUser, fromUser);

        if(friendship == null) {
            return true;
        }

        if(friendship.accept) {
            throw new AlreadyFriendshipException();
        }
        return false;
    }

    @Override
    @Transactional
    public void requestCancel(User fromUser, User toUser) {
        friendshipRepository.findBySelfJoin(fromUser, toUser).orElseThrow(NotFoundFriendshipRequestException::new);
        friendshipRepository.deleteByFromUserAndToUser(fromUser, toUser);
    }

    @Override
    @Transactional(readOnly = true)
    public FriendshipAllListResponseDto getFriendship(User owner) {
        List<Friendship> friendships = friendshipRepository.findAllFriendsByUser(owner);

        return new FriendshipAllListResponseDto().toDo(friendships);
    }

    @Override
    @Transactional(readOnly = true)
    public FriendshipSendListResponseDto getFriendshipSend(User owner) {
        List<Friendship> friendships = friendshipRepository.findSendFriendsByUser(owner);

        return new FriendshipSendListResponseDto().toDo(friendships);
    }

    @Override
    @Transactional(readOnly = true)
    public FriendshipReceiveListResponseDto getFriendshipReceive(User owner) {
        List<Friendship> friendships = friendshipRepository.findReceiveFriendsByUser(owner);

        return new FriendshipReceiveListResponseDto().toDO(friendships);
    }

    @Transactional
    public void deleteFriendship(User responseUser, User requestUser) {
        friendshipRepository.deleteByFromUserAndToUser(requestUser, responseUser);
    }
}
