package cord.eoeo.momentwo.subAlbum.adapter.out;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;
import cord.eoeo.momentwo.subAlbum.advice.exception.NotFoundSubAlbumException;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumManager;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumRepository;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubAlbumManagerImpl implements SubAlbumManager {
    private final SubAlbumRepository subAlbumRepository;

    @Override
    @Transactional(readOnly = true)
    public SubAlbum getSubAlbumInfo(long subAlbum) {
        return subAlbumRepository.getSubAlbumInfo(subAlbum).orElseThrow(NotFoundSubAlbumException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public SubAlbumListResponseDto getSubAlbumList(long albumId) {
        List<SubAlbum> subAlbumList = subAlbumRepository.getSubAlbumListByAlbumId(albumId);
        return new SubAlbumListResponseDto().toDo(subAlbumList);
    }

    @Override
    @Transactional
    public void setSubAlbumTitle(long subAlbumId, String editTitle) {
        SubAlbum subAlbum = subAlbumRepository.findById(subAlbumId)
                .orElseThrow(NotFoundSubAlbumException::new);
        subAlbum.setSubAlbumTitle(editTitle);
        subAlbumRepository.save(subAlbum);
    }

    @Override
    public void deleteSubAlbum(List<Long> subAlbumIds) {
        subAlbumRepository.deleteBySubAlbumIds(subAlbumIds);
    }
}
