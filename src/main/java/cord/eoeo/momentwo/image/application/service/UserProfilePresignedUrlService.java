package cord.eoeo.momentwo.image.application.service;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.adapter.dto.UserPresignedRequestDto;
import cord.eoeo.momentwo.image.application.port.in.UserProfilePresignedUrlUseCase;
import cord.eoeo.momentwo.image.application.port.out.ImageDeletePort;
import cord.eoeo.momentwo.image.application.port.out.MakeImagePresignedUrlPort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfilePresignedUrlService implements UserProfilePresignedUrlUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final ImageDeletePort imageDeletePort;
    private final MakeImagePresignedUrlPort makeImagePresignedUrlPort;
    private final S3Manager s3Manager;

    @Override
    public PresignedResponseDto userProfilePresignedUrl(UserPresignedRequestDto userPresignedRequestDto) {
        User user = userNicknameValidPort.authenticationValid();

        // 이미지 삭제(현재) -> 기존 이미지가 없다면 업로드한 이미지만 저장, 기존 이미지가 있다면 삭제 진행
        imageDeletePort.imageDelete(s3Manager.getProfileUsersPath() + user.getUserProfileImage()).join();
        return new PresignedResponseDto()
                .toDo(makeImagePresignedUrlPort.makeImagePresignedUrl(
                        userPresignedRequestDto.getExtension(), s3Manager.getProfileUsersPath())
                );
    }
}
