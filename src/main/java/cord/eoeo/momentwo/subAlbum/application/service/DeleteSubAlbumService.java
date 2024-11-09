package cord.eoeo.momentwo.subAlbum.application.service;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumDeleteRequestDto;
import cord.eoeo.momentwo.subAlbum.advice.exception.NotDeleteSubAlbumException;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.in.DeleteSubAlbumUseCase;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.DeleteSubAlbumPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSubAlbumService implements DeleteSubAlbumUseCase {
    private final DeleteSubAlbumPort deleteSubAlbumPort;

    @Override
    @CheckAlbumAccessRules
    public void deleteSubAlbums(SubAlbumDeleteRequestDto subAlbumDeleteRequestDto) {
        if(subAlbumDeleteRequestDto.getSubAlbumIds().isEmpty()) {
            throw new NotDeleteSubAlbumException();
        }
        deleteSubAlbumPort.deleteSubAlbum(subAlbumDeleteRequestDto.getSubAlbumIds());
    }
}
