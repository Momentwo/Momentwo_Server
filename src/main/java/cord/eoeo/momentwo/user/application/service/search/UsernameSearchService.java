package cord.eoeo.momentwo.user.application.service.search;

import cord.eoeo.momentwo.user.adapter.dto.in.SearchUsernameRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.in.search.UsernameSearchUseCase;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNameAndPhoneRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsernameSearchService implements UsernameSearchUseCase {
    private final UserFindNameAndPhoneRepo userFindNameAndPhoneRepo;

    @Override
    @Transactional(readOnly = true)
    public SearchUsernameResponseDto usernameSearch(SearchUsernameRequestDto searchUsernameRequestDto) {
        User user = userFindNameAndPhoneRepo.findByNameAndPhone(
                searchUsernameRequestDto.getName(),
                searchUsernameRequestDto.getPhone()
        ).orElseThrow(NotFoundUserException::new);
        return new SearchUsernameResponseDto().toDo(user);
    }
}
