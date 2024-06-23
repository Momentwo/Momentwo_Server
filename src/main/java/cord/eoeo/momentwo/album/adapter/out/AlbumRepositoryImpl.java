package cord.eoeo.momentwo.album.adapter.out;

import cord.eoeo.momentwo.album.application.port.out.AlbumJpaRepository;
import cord.eoeo.momentwo.album.application.port.out.AlbumRepository;
import cord.eoeo.momentwo.album.domain.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlbumRepositoryImpl implements AlbumRepository {
    private final AlbumJpaRepository albumJpaRepository;

    @Override
    public void save(Album album) {
        albumJpaRepository.save(album);
    }

    @Override
    public Optional<Album> findById(long id) {
        return albumJpaRepository.findById(id);
    }

    @Override
    public void deleteAlbum(long id) {
        albumJpaRepository.deleteAlbum(id);
    }
}
