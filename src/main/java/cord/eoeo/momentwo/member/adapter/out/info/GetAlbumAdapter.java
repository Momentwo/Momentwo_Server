package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumPort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class GetAlbumAdapter implements GetAlbumPort {
    private final AlbumGenericRepo albumGenericRepo;

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<Album> getAlbum(long id) {
        return CompletableFuture.supplyAsync(()
                -> albumGenericRepo.findById(id).orElseThrow(NotFoundAlbumException::new));
    }
}
