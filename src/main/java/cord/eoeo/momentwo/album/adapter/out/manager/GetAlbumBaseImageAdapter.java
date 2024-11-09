package cord.eoeo.momentwo.album.adapter.out.manager;

import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumBaseImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAlbumBaseImageAdapter implements GetAlbumBaseImagePort {
    @Override
    public String getBaseImage() {
        return "";
    }
}
