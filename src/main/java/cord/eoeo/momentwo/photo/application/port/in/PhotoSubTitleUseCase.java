package cord.eoeo.momentwo.photo.application.port.in;

import cord.eoeo.momentwo.photo.adapter.dto.sub.PhotoSubTitleGetRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.sub.PhotoSubTitleListResponseDto;
import cord.eoeo.momentwo.photo.adapter.dto.sub.SubTitleCreateRequestDto;

import java.util.List;

public interface PhotoSubTitleUseCase {
    // 소제목 생성
    void createPhotoSubTitle(SubTitleCreateRequestDto subTitleCreateRequestDto);
    List<PhotoSubTitleListResponseDto> getPhotoSubTitle(PhotoSubTitleGetRequestDto photoSubTitleGetRequestDto);
}
