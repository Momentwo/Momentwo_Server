package cord.eoeo.momentwo.subAlbum.adapter.out.get;

import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumGenericRepo;
import cord.eoeo.momentwo.subAlbum.application.port.out.get.GetSubAlbumInfoRepo;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetSubAlbumInfoRepoAdapter implements GetSubAlbumInfoRepo {
    private final SubAlbumGenericRepo subAlbumGenericRepo;

    @Override
    public Optional<SubAlbum> getSubAlbumInfo(long subAlbum) {
        return subAlbumGenericRepo.findById(subAlbum);
    }
}
