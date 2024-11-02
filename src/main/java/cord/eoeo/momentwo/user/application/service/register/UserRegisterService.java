package cord.eoeo.momentwo.user.application.service.register;

import cord.eoeo.momentwo.elasticsearch.application.port.out.UserElasticSearchManager;
import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;
import cord.eoeo.momentwo.user.application.port.in.register.UserRegisterUseCase;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService implements UserRegisterUseCase {
    private final String USER_BASE_IMAGE = "";
    private final UserGenericRepo userGenericRepo;
    private final PasswordEncoder passwordEncoder;
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
                userRegisterRequestDto.getPhone(),
                USER_BASE_IMAGE
        );
        userGenericRepo.save(newUser);
        userElasticSearchManager.save(newUser);
    }
}