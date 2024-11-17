package cord.eoeo.momentwo.comment.adapter.out.manager;

import cord.eoeo.momentwo.comment.advice.exception.NotFoundCommentException;
import cord.eoeo.momentwo.comment.application.port.out.CommentGenericRepo;
import cord.eoeo.momentwo.comment.application.port.out.manager.IsAdminCompPort;
import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindGradeByAlbumIdAndUserIdRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsAdminCompAdapter implements IsAdminCompPort {
    private final MemberFindGradeByAlbumIdAndUserIdRepo memberFindGradeByAlbumIdAndUserIdRepo;
    private final CommentGenericRepo commentGenericRepo;

    @Override
    public boolean isAdmin(long albumId, long commentId, User user) {
        Comment comment = commentGenericRepo.findById(commentId).orElseThrow(NotFoundCommentException::new);
        int inputUserPriority = memberFindGradeByAlbumIdAndUserIdRepo
                .findMemberGradeByAlbumIdAndUserId(albumId, user.getId()).getRules().getPriority();
        int commentUserPriority = memberFindGradeByAlbumIdAndUserIdRepo
                .findMemberGradeByAlbumIdAndUserId(albumId, comment.getUser().getId()).getRules().getPriority();

        return inputUserPriority < commentUserPriority; // 우선순위 : 관리자 1, 부관리자 2
    }
}
