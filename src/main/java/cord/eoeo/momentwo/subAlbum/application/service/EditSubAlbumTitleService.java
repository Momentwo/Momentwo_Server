package cord.eoeo.momentwo.subAlbum.application.service;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumEditTitleRequestDto;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.in.EditSubAlbumUseCase;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.SetSubAlbumTitlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditSubAlbumTitleService implements EditSubAlbumUseCase {
    private final SetSubAlbumTitlePort setSubAlbumTitlePort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void editSubAlbumTitle(SubAlbumEditTitleRequestDto subAlbumEditTitleRequestDto) {
        setSubAlbumTitlePort.setSubAlbumTitle(
                subAlbumEditTitleRequestDto.getSubAlbumId(),
                subAlbumEditTitleRequestDto.getEditTitle()
        );
    }
}
