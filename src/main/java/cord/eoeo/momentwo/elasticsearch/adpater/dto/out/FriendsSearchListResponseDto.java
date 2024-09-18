package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsSearchListResponseDto {
    private List<FriendsSearchResponseDto> friendsList;

    public FriendsSearchListResponseDto toDo(List<FriendsDocument> friendsDocuments) {
        return new FriendsSearchListResponseDto(
                friendsDocuments.stream()
                        .map(friendsDocument
                                -> new FriendsSearchResponseDto().toDo(friendsDocument))
                        .collect(Collectors.toList())
        );
    }
}
