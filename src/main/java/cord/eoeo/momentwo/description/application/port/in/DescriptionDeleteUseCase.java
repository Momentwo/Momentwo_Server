package cord.eoeo.momentwo.description.application.port.in;

public interface DescriptionDeleteUseCase {
    /**
     * 설명 삭제
     * @param albumId
     * @param photoId
     * albumId : 앨범 아이디
     * photoId : 사진 아이디
     */
    void deleteDescription(Long albumId, Long photoId);
}
