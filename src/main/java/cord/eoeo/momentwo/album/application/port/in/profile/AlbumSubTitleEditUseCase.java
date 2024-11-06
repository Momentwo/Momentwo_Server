package cord.eoeo.momentwo.album.application.port.in.profile;

import cord.eoeo.momentwo.album.adapter.dto.AlbumSubTitleEditRequestDto;

public interface AlbumSubTitleEditUseCase {
    /**
     * 앨범 서브 타이틀 수정
     * @param subTitleEditRequestDto
     * albumId : 앨범 아이디
     * subTitle : 변경할 제목
     */
    void albumSubTitleEdit(AlbumSubTitleEditRequestDto subTitleEditRequestDto);
}
