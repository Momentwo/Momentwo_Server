package cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager;

public interface DeleteByLikesPort {
    void deleteByLikes(long id, String nickname);
}
