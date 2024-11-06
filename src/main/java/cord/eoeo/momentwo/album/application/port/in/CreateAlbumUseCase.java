package cord.eoeo.momentwo.album.application.port.in;

import cord.eoeo.momentwo.album.adapter.dto.AlbumCreateRequestDto;

public interface CreateAlbumUseCase {
    /**
     * 앨범 생성
     * @param albumCreateRequestDto
     * createTitle : 앨범 제목
     * doInviteNicknameList : 초대 멤버 (empty ok)
     * filename : 앨범 프로필 설정 (empty ok)
     */
    void createAlbums(AlbumCreateRequestDto albumCreateRequestDto);
}
