package cord.eoeo.momentwo.album.application.port.in;

import cord.eoeo.momentwo.album.adapter.dto.AlbumProfileRequestDto;
import cord.eoeo.momentwo.album.adapter.dto.AlbumProfileUploadRequestDto;
import cord.eoeo.momentwo.album.adapter.dto.AlbumSubTitleEditRequestDto;

public interface AlbumProfileUseCase {
    // 프로필 변경 (업로드)
    void profileUpload(AlbumProfileUploadRequestDto uploadRequestDto);

    // 프로필 삭제
    void profileDelete(AlbumProfileRequestDto albumProfileRequestDto);

    // 서브 타이틀 수정
    void albumSubTitleEdit(AlbumSubTitleEditRequestDto subTitleEditRequestDto);

    // 서브 타이틀 삭제
    void albumSubTitleDelete(AlbumProfileRequestDto albumProfileRequestDto);
}
