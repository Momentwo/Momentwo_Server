package cord.eoeo.momentwo.elasticsearch.adpater.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.UserSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.UserSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.UserSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserSearchController {
    private final UserSearchUseCase userSearchUseCase;

    @GetMapping("/users/search")
    @ResponseStatus(HttpStatus.OK)
    public UserSearchListResponseDto getUserElasticSearch(@ModelAttribute @Valid UserSearchRequestDto userSearchRequestDto) {
        return userSearchUseCase.getUserElasticSearch(userSearchRequestDto);
    }
}
