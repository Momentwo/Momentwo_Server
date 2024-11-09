package cord.eoeo.momentwo.description.application.port.in;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;

public interface DescriptionCreateUseCase {
    /**
     * 설명 생성
     * @param descriptionCreateRequestDto
     * albumId : 앨범 아이디
     * photoId : 사진 아이디
     * description : 설명
     */
    void createDescription(DescriptionCreateRequestDto descriptionCreateRequestDto);
}
