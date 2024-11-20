package cord.eoeo.momentwo.album.application.port.in.profile;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumProfileRequestDto;

public interface AlbumSubTitleDeleteUseCase {
    /**
     * 앨범 서브 타이틀 삭제
     * @param albumProfileRequestDto
     * albumId : 앨범 아이디
     */
    void albumSubTitleDelete(AlbumProfileRequestDto albumProfileRequestDto);
}
