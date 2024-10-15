package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchListResponseDto {
    private List<UserSearchResponseDto> searchUsers;
    private long page;
    private long size;
    private long totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public UserSearchListResponseDto toDo(Page<UserDocument> userDocuments) {
        return new UserSearchListResponseDto(
                userDocuments.stream().map(user -> new UserSearchResponseDto().toDo(user))
                        .collect(Collectors.toList()),
                userDocuments.getNumber(),
                userDocuments.getSize(),
                userDocuments.getTotalPages(),
                userDocuments.getTotalElements(),
                userDocuments.hasNext(),
                userDocuments.hasPrevious()
        );
    }
}
