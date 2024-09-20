package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsSearchListResponseDto {
    private List<FriendsSearchResponseDto> friendsList;
    private long page;
    private long size;
    private long totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public FriendsSearchListResponseDto toDo(Page<FriendsDocument> friendsDocuments) {
        return new FriendsSearchListResponseDto(
                friendsDocuments.stream()
                        .map(friendsDocument
                                -> new FriendsSearchResponseDto().toDo(friendsDocument))
                        .collect(Collectors.toList()),
                friendsDocuments.getNumber(),
                friendsDocuments.getSize(),
                friendsDocuments.getTotalPages(),
                friendsDocuments.getTotalElements(),
                friendsDocuments.hasNext(),
                friendsDocuments.hasPrevious()
        );
    }
}
