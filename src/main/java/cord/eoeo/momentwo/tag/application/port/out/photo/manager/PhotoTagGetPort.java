package cord.eoeo.momentwo.tag.application.port.out.photo.manager;

import java.util.List;

public interface PhotoTagGetPort {
    List<String> photoTagGet(long photoId);
}
