package cord.eoeo.momentwo.elasticsearch.adpater.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.UserSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.FriendsSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.FriendsSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class FriendsSearchController {
    private final FriendsSearchUseCase friendsSearchUseCase;

    @GetMapping("/friends/search")
    @ResponseStatus(HttpStatus.OK)
    public FriendsSearchListResponseDto getFriends(@ModelAttribute @Valid UserSearchRequestDto userSearchRequestDto) {
        return friendsSearchUseCase.getFriends(userSearchRequestDto);
    }
}
