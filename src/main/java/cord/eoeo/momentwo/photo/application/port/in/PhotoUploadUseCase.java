package cord.eoeo.momentwo.photo.application.port.in;

import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;

public interface PhotoUploadUseCase {
    /**
     * 사진 업로드
     * @param photoUploadRequestDto
     * albumId : 앨범 아이디
     * subAlbumId : 서브 앨범 아이디
     * filename : 경로
     */
    void photoUpload(PhotoUploadRequestDto photoUploadRequestDto);
}
