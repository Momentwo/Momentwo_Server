package cord.eoeo.momentwo.album.application.port.in.profile;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumProfileUploadRequestDto;

public interface ProfileUploadUseCase {
    /**
     * 앨범 프로필 변경 (업로드)
     * @param uploadRequestDto
     * albumId : 앨범 아이디
     * filename : 경로
     */
    void profileUpload(AlbumProfileUploadRequestDto uploadRequestDto);
}
