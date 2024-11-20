package cord.eoeo.momentwo.album.application.port.in.profile;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumProfileRequestDto;

public interface ProfileDeleteUseCase {
    /**
     * 앨범 프로필 삭제
     * @param albumProfileRequestDto
     * albumId : 앨범 아이디
     */
    // 프로필 삭제
    void profileDelete(AlbumProfileRequestDto albumProfileRequestDto);
}
