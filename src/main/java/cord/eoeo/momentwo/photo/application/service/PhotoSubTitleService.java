package cord.eoeo.momentwo.photo.application.service;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.photo.adapter.dto.sub.PhotoSubTitleGetRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.sub.PhotoSubTitleListResponseDto;
import cord.eoeo.momentwo.photo.adapter.dto.sub.SubTitleCreateRequestDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoSubTitleUseCase;
import cord.eoeo.momentwo.photo.application.port.out.PhotoSubTitleManager;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumManager;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoSubTitleService implements PhotoSubTitleUseCase {
    private final PhotoSubTitleManager photoSubTitleManager;
    private final AlbumManager albumManager;
    private final SubAlbumManager subAlbumManager;

    @Override
    @CheckAlbumAccessRules
    public void createPhotoSubTitle(SubTitleCreateRequestDto subTitleCreateRequestDto) {
        albumManager.getAlbumInfo(subTitleCreateRequestDto.getAlbumId());
        SubAlbum subAlbum = subAlbumManager.getSubAlbumInfo(subTitleCreateRequestDto.getSubAlbumId());
        photoSubTitleManager.createPhotoSubTitle(
                subAlbum,
                subTitleCreateRequestDto.getSubTitle()
        );
    }

    @Override
    @CheckAlbumAccessRules
    public List<PhotoSubTitleListResponseDto> getPhotoSubTitle(PhotoSubTitleGetRequestDto photoSubTitleGetRequestDto) {
        return photoSubTitleManager.getPhotoSubTitle(photoSubTitleGetRequestDto.getSubAlbumId());
    }
}
