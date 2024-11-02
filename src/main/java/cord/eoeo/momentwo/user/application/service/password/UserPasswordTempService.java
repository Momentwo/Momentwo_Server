package cord.eoeo.momentwo.user.application.service.password;

import cord.eoeo.momentwo.user.adapter.dto.in.TempPasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.TempPasswordResponseDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.in.password.UserPasswordTempUseCase;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindUsernameAndPhoneRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPasswordTempService implements UserPasswordTempUseCase {
    private static final int PASSWORD_LENGTH = 10;

    private final UserGenericRepo userGenericRepo;
    private final UserFindUsernameAndPhoneRepo userFindUsernameAndPhoneRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public TempPasswordResponseDto tempPassword(TempPasswordRequestDto tempPasswordRequestDto) {
        User user = userFindUsernameAndPhoneRepo.findByUsernameAndPhone(
                tempPasswordRequestDto.getUsername(),
                tempPasswordRequestDto.getPhone()
        ).orElseThrow(NotFoundUserException::new);

        String randomPassword = UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, PASSWORD_LENGTH) + "!@#";

        user.setPassword(passwordEncoder.encoder(randomPassword));
        userGenericRepo.save(user);

        return new TempPasswordResponseDto().toDo(randomPassword);
    }
}
