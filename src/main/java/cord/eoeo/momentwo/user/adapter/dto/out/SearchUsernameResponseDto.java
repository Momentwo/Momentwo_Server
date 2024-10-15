package cord.eoeo.momentwo.user.adapter.dto.out;

import cord.eoeo.momentwo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchUsernameResponseDto {
    private String username;

    public SearchUsernameResponseDto toDo(User user) {
        return new SearchUsernameResponseDto(user.getUsername());
    }
}
