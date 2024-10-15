package cord.eoeo.momentwo.subAlbum.adapter.out;

import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumJpaRepository;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumRepository;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubAlbumRepositoryImpl implements SubAlbumRepository {
    private final SubAlbumJpaRepository subAlbumJpaRepository;

    @Override
    public void save(SubAlbum subAlbum) {
        subAlbumJpaRepository.save(subAlbum);
    }

    @Override
    public Optional<SubAlbum> getSubAlbumInfo(long subAlbum) {
        return subAlbumJpaRepository.findById(subAlbum);
    }

    @Override
    public List<SubAlbum> getSubAlbumListByAlbumId(long albumId) {
        return subAlbumJpaRepository.getSubAlbumListByAlbumId(albumId);
    }

    @Override
    public Optional<SubAlbum> findById(long subAlbumId) {
        return subAlbumJpaRepository.findById(subAlbumId);
    }

    @Override
    public void deleteBySubAlbumIds(List<Long> subAlbumIds) {
        subAlbumJpaRepository.deleteBySubAlbumIds(subAlbumIds);
    }
}
