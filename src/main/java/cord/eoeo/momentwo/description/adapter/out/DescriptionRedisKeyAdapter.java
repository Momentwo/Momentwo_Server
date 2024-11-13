package cord.eoeo.momentwo.description.adapter.out;

import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisKeyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DescriptionRedisKeyAdapter implements DescriptionRedisKeyPort {
    @Override
    public String getKey(long photoId) {
        return "description/" + photoId;
    }
}
