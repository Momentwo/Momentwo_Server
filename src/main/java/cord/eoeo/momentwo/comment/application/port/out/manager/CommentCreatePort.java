package cord.eoeo.momentwo.comment.application.port.out.manager;

public interface CommentCreatePort {
    void commentCreate(String comments, long photoId);
}
