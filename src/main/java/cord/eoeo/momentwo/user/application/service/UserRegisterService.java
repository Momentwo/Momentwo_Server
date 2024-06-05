package cord.eoeo.momentwo.user.application.service;

import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;
import cord.eoeo.momentwo.user.advice.exception.DuplicateNicknameException;
import cord.eoeo.momentwo.user.advice.exception.DuplicateUsernameException;
import cord.eoeo.momentwo.user.application.port.in.UserRegisterUseCase;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService implements UserRegisterUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public void register(UserRegisterRequestDto userRegisterRequestDto) {
        // 유저 이메일(Id) 중복체크
        checkUserIdDuplicate(userRegisterRequestDto.getUsername());

        // 유저 닉네임 중복 체크
        checkUserNicknameDuplicate(userRegisterRequestDto.getNickname());

        User newUser = new User(
                userRegisterRequestDto.getName(),
                userRegisterRequestDto.getUsername(),
                passwordEncoder.encoder(userRegisterRequestDto.getPassword()),
                userRegisterRequestDto.getNickname(),
                userRegisterRequestDto.getBirthday(),
                userRegisterRequestDto.getPhone(),
                userRegisterRequestDto.getAddress()
        );
        userRepository.save(newUser);
    }

    private void checkUserIdDuplicate(String username) {
        if(userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException();
        }
    }

    private void checkUserNicknameDuplicate(String nickname) {
        if(userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }
}
