package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.adapter.dto.sub.PhotoSubTitleListResponseDto;
import cord.eoeo.momentwo.photo.domain.PhotoSubTitle;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;

import java.util.List;

public interface PhotoSubTitleManager {
    void createPhotoSubTitle(SubAlbum subAlbum, String subTitle);
    PhotoSubTitle getPhotoSubTitleInfo(long photoSubTitleId);
    List<PhotoSubTitleListResponseDto> getPhotoSubTitle(long subAlbumId);
}
