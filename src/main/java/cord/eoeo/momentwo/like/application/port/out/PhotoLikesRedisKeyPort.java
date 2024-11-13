package cord.eoeo.momentwo.like.application.port.out;

public interface PhotoLikesRedisKeyPort {
    String getKey(long photoId);
}
