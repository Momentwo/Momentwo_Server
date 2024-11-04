package cord.eoeo.momentwo.image.adapter.in;

import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.adapter.dto.UserPresignedRequestDto;
import cord.eoeo.momentwo.image.application.port.in.UserProfilePresignedUrlUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserProfilePresignedUrlController {
    private final UserProfilePresignedUrlUseCase userProfilePresignedUrlUseCase;

    @PostMapping("/images/users/profiles/presigned")
    @ResponseStatus(HttpStatus.OK)
    public PresignedResponseDto userProfilePresignedUrl(
            @ModelAttribute @Valid UserPresignedRequestDto userPresignedRequestDto) {
        return userProfilePresignedUrlUseCase.userProfilePresignedUrl(userPresignedRequestDto);
    }
}
