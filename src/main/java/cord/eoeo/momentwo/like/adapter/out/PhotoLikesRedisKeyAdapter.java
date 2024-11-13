package cord.eoeo.momentwo.like.adapter.out;

import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRedisKeyPort;
import org.springframework.stereotype.Component;

@Component
public class PhotoLikesRedisKeyAdapter implements PhotoLikesRedisKeyPort {

    @Override
    public String getKey(long photoId) {
        return "photoLikes/" + photoId;
    }
}
