package cord.eoeo.momentwo.user.application.port.in.profile;

import cord.eoeo.momentwo.user.adapter.dto.in.UserProfileUploadRequestDto;

public interface UserProfileImageUploadUseCase {
    /**
     * 유저 프로필 사진 업로드
     * @param userProfileUploadRequestDto
     * filename : S3 이미지 경로
     */
    void usersProfilesImageUpload(UserProfileUploadRequestDto userProfileUploadRequestDto);
}
