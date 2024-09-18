package cord.eoeo.momentwo.user.application.service;

import cord.eoeo.momentwo.elasticsearch.adpater.out.UserElasticSearchManager;
import cord.eoeo.momentwo.user.adapter.dto.in.EmailAvailabilityDto;
import cord.eoeo.momentwo.user.adapter.dto.in.NicknameAvailabilityDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;
import cord.eoeo.momentwo.user.application.port.in.UserRegisterUseCase;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.UserRegisterAsync;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserRegisterService implements UserRegisterUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRegisterAsync userRegisterAsync;
    private final UserElasticSearchManager userElasticSearchManager;

    @Transactional
    @Override
    public void register(UserRegisterRequestDto userRegisterRequestDto) {
        User newUser = new User(
                userRegisterRequestDto.getName(),
                userRegisterRequestDto.getUsername(),
                passwordEncoder.encoder(userRegisterRequestDto.getPassword()),
                userRegisterRequestDto.getNickname(),
                userRegisterRequestDto.getBirthday(),
                userRegisterRequestDto.getPhone()
        );
        userRepository.save(newUser);
        userElasticSearchManager.save(newUser);
    }

    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<Void> checkEmailAvailability(EmailAvailabilityDto emailAvailabilityDto) {
        // 유저 이메일(Id) 중복체크
        return userRegisterAsync.checkUsernameDuplicate(emailAvailabilityDto.getUsername());
    }

    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<Void> checkNicknameAvailability(NicknameAvailabilityDto nicknameAvailabilityDto) {
        // 유저 닉네임 중복 체크
        return userRegisterAsync.checkUserNicknameDuplicate(nicknameAvailabilityDto.getNickname());
    }
}