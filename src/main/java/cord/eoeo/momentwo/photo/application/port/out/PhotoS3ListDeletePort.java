package cord.eoeo.momentwo.photo.application.port.out;

import java.util.List;

public interface PhotoS3ListDeletePort {
    void photoS3ListDelete(List<String> keys);
}
