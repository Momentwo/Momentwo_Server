package cord.eoeo.momentwo.subAlbum.application.service;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.in.CreateSubAlbumUseCase;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumGenericRepo;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateSubAlbumService implements CreateSubAlbumUseCase {
    private final AlbumManager albumManager;
    private final SubAlbumGenericRepo subAlbumGenericRepo;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void createSubAlbum(SubAlbumCreateRequestDto subAlbumCreateRequestDto) {
        Album album = albumManager.getAlbumInfo(subAlbumCreateRequestDto.getAlbumId());
        SubAlbum newSubAlbum = new SubAlbum(subAlbumCreateRequestDto.getTitle(), album);
        subAlbumGenericRepo.save(newSubAlbum);
    }

}
