package cord.eoeo.momentwo.image.application.port.in;

import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.adapter.dto.UserPresignedRequestDto;

public interface UserProfilePresignedUrlUseCase {
    /**
     * 유저 프로필 프리사인드 URL 요청
     * @param userPresignedRequestDto
     * extension : 확장자
     * @return : 프리사인드 URL
     */
    PresignedResponseDto userProfilePresignedUrl(UserPresignedRequestDto userPresignedRequestDto);
}
