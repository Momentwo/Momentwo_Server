package cord.eoeo.momentwo.description.application.port.in;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;

public interface DescriptionEditUseCase {
    /**
     * 설명 수정
     * @param descriptionEditRequestDto
     * albumId : 앨범 아이디
     * photoId : 사진 아이디
     * editDescription : 수정할 설명
     */
    void editDescription(DescriptionEditRequestDto descriptionEditRequestDto);
}
