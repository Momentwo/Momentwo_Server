package cord.eoeo.momentwo.album.adapter.out.manager;

import cord.eoeo.momentwo.album.application.port.out.manager.AlbumS3ImageDeletePort;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.application.port.out.ImageListDeletePort;
import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindAllImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlbumS3ImageDeleteAdapter implements AlbumS3ImageDeletePort {
    private final PhotoFindAllImagePort photoFindAllImagePort;
    private final ImageListDeletePort imageListDeletePort;
    private final S3Manager s3Manager;

    @Override
    public void s3ImageDelete(long albumId) {
        List<String> keys = photoFindAllImagePort.photoFindAllImage(albumId);
        int batchSize = 1000;
        keys.parallelStream()
                .map(key -> key.substring(s3Manager.getBaseDomain().length()))
                .collect(Collectors.groupingBy(key -> keys.indexOf(key) / batchSize))
                .forEach((group, batch) -> imageListDeletePort.imageListDelete(batch));
    }
}
