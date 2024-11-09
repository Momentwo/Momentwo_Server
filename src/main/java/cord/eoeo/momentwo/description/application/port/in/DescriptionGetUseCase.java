package cord.eoeo.momentwo.description.application.port.in;

import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;

public interface DescriptionGetUseCase {
    /**
     * 설명 조회
     * @param albumId : 앨범 아이디
     * @param photoId : 사진 아이디
     * @return : 설명 내용
     */
    DescriptionResponseDto getDescription(long albumId, long photoId);
}
