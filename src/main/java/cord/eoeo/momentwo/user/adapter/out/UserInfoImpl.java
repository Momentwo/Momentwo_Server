package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.elasticsearch.application.port.out.UserElasticSearchManager;
import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.out.TempPasswordResponseDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.advice.exception.PasswordMisMatchException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.UserInfo;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class UserInfoImpl implements UserInfo {
    private static final int PASSWORD_LENGTH = 10;

    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;
    private final PasswordEncoder passwordEncoder;
    private final S3Manager s3Manager;
    private final UserElasticSearchManager userElasticSearchManager;

    @Override
    @Transactional(readOnly = true)
    public SearchUsernameResponseDto searchUsername(String name, String phone) {
        User user = userRepository.findByNameAndPhone(name, phone)
                .orElseThrow(NotFoundUserException::new);
        return new SearchUsernameResponseDto().toDo(user);
    }

    @Override
    @Transactional
    public TempPasswordResponseDto tempPassword(String username, String phone) {
        User user = userRepository.findByUsernameAndPhone(username, phone)
                .orElseThrow(NotFoundUserException::new);

        String randomPassword = UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, PASSWORD_LENGTH) + "!@#";

        user.setPassword(passwordEncoder.encoder(randomPassword));
        userRepository.save(user);

        return new TempPasswordResponseDto().toDo(randomPassword);
    }

    @Override
    @Transactional
    public void changePassword(String newPassword, String newPasswordMatch) {
        if(!newPassword.equals(newPasswordMatch)) {
            throw new PasswordMisMatchException();
        }

        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        user.setPassword(passwordEncoder.encoder(newPasswordMatch));
        userRepository.save(user);
    }

    @Override
    public void usersProfilesUpload(String filename) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        user.setUserProfileImage(s3Manager.getBaseDomain() + filename);
        userRepository.save(user);
        userElasticSearchManager.userInfoChange(user);
    }
}
