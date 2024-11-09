package cord.eoeo.momentwo.album.adapter.out.manager;

import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumSubTitlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAlbumSubTitleAdapter implements GetAlbumSubTitlePort {
    @Override
    public String getSubTitle() {
        return "";
    }
}
