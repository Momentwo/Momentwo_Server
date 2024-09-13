package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchListResponseDto {
    private List<UserSearchResponseDto> searchUsers;

    public UserSearchListResponseDto toDo(List<UserDocument> userDocuments) {
        return new UserSearchListResponseDto(
                userDocuments.stream().map(user -> new UserSearchResponseDto().toDo(user))
                        .collect(Collectors.toList())
        );
    }
}
