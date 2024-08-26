package cord.eoeo.momentwo.subAlbum.application.service;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumDeleteRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumEditTitleRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;
import cord.eoeo.momentwo.subAlbum.advice.exception.NotDeleteSubAlbumException;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.in.SubAlbumUseCase;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumManager;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumRepository;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubAlbumService implements SubAlbumUseCase {
    private final AlbumManager albumManager;
    private final SubAlbumRepository subAlbumRepository;
    private final SubAlbumManager subAlbumManager;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void createSubAlbum(SubAlbumCreateRequestDto subAlbumCreateRequestDto) {
        Album album = albumManager.getAlbumInfo(subAlbumCreateRequestDto.getAlbumId());
        SubAlbum newSubAlbum = new SubAlbum(subAlbumCreateRequestDto.getTitle(), album);
        subAlbumRepository.save(newSubAlbum);
    }

    @Override
    @CheckAlbumAccessRules
    public SubAlbumListResponseDto getSubAlbums(long albumId) {
        return subAlbumManager.getSubAlbumList(albumId);
    }

    @Override
    @CheckAlbumAccessRules
    public void editSubAlbumTitle(SubAlbumEditTitleRequestDto subAlbumEditTitleRequestDto) {
        subAlbumManager.setSubAlbumTitle(
                subAlbumEditTitleRequestDto.getSubAlbumId(),
                subAlbumEditTitleRequestDto.getEditTitle()
        );
    }

    @Override
    @CheckAlbumAccessRules
    public void deleteSubAlbums(SubAlbumDeleteRequestDto subAlbumDeleteRequestDto) {
        if(subAlbumDeleteRequestDto.getSubAlbumIds().isEmpty()) {
            throw new NotDeleteSubAlbumException();
        }
        subAlbumManager.deleteSubAlbum(subAlbumDeleteRequestDto.getSubAlbumIds());
    }
}
