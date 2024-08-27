package cord.eoeo.momentwo.photo.adapter.out;

import cord.eoeo.momentwo.photo.adapter.dto.sub.PhotoSubTitleListResponseDto;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoSubTitleException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoSubTitleManager;
import cord.eoeo.momentwo.photo.application.port.out.PhotoSubTitleRepository;
import cord.eoeo.momentwo.photo.domain.PhotoSubTitle;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PhotoSubTitleManagerImpl implements PhotoSubTitleManager {
    private final PhotoSubTitleRepository photoSubTitleRepository;

    @Override
    public void createPhotoSubTitle(SubAlbum subAlbum, String subTitle) {
        PhotoSubTitle photoSubTitle = new PhotoSubTitle(subTitle, subAlbum);
        photoSubTitleRepository.save(photoSubTitle);
    }

    @Override
    public PhotoSubTitle getPhotoSubTitleInfo(long photoSubTitleId) {
        return photoSubTitleRepository.findById(photoSubTitleId)
                .orElseThrow(NotFoundPhotoSubTitleException::new);
    }

    @Override
    public List<PhotoSubTitleListResponseDto> getPhotoSubTitle(long subAlbumId) {
        List<PhotoSubTitle> photoSubTitles = photoSubTitleRepository.findSubTitleBySubAlbumId(subAlbumId);
        return photoSubTitles.stream().map(photoSubTitle -> new PhotoSubTitleListResponseDto().toDo(photoSubTitle))
                .collect(Collectors.toList());
    }
}
