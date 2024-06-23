package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumCreateRequestDto;
import cord.eoeo.momentwo.album.adapter.dto.AlbumTitleEditRequestDto;
import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.port.in.AlbumUseCase;
import cord.eoeo.momentwo.album.application.port.out.AlbumRepository;
import cord.eoeo.momentwo.album.domain.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumService implements AlbumUseCase {
    private final AlbumRepository albumRepository;

    @Transactional
    @Override
    public void createAlbums(AlbumCreateRequestDto albumCreateRequestDto) {
        Album newAlbum = new Album(albumCreateRequestDto.getCreateTitle());
        albumRepository.save(newAlbum);
    }

    @Transactional
    @Override
    public void editAlbumsTitle(long id, AlbumTitleEditRequestDto albumTitleEditRequestDto) {
        Album album = albumRepository.findById(id).orElseThrow(NotFoundAlbumException::new);
        album.setTitle(albumTitleEditRequestDto.getEditTitle());
        albumRepository.save(album);
    }

    @Transactional
    @Override
    public void deleteAlbums(long id) {
        albumRepository.deleteAlbum(id);
    }
}
