package cord.eoeo.momentwo.subAlbum.application.service;

import cord.eoeo.momentwo.album.application.port.out.manager.AlbumS3ImageDeletePort;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumDeleteRequestDto;
import cord.eoeo.momentwo.subAlbum.advice.exception.NotDeleteSubAlbumException;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.in.DeleteSubAlbumUseCase;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.DeleteSubAlbumPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteSubAlbumService implements DeleteSubAlbumUseCase {
    private final DeleteSubAlbumPort deleteSubAlbumPort;
    private final AlbumS3ImageDeletePort albumS3ImageDeletePort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void deleteSubAlbums(SubAlbumDeleteRequestDto subAlbumDeleteRequestDto) {
        if(subAlbumDeleteRequestDto.getSubAlbumIds().isEmpty()) {
            throw new NotDeleteSubAlbumException();
        }

        // S3 저장소 삭제
        albumS3ImageDeletePort.s3ImageDelete(subAlbumDeleteRequestDto.getAlbumId());

        // DB 데이터 삭제
        deleteSubAlbumPort.deleteSubAlbum(subAlbumDeleteRequestDto.getSubAlbumIds());
    }
}
