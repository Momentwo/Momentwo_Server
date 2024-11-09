package cord.eoeo.momentwo.description.application.port.in;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;

public interface DescriptionDeleteUseCase {
    /**
     * 설명 삭제
     * @param descriptionRequestDto
     * albumId : 앨범 아이디
     * photoId : 사진 아이디
     */
    void deleteDescription(DescriptionRequestDto descriptionRequestDto);
}
