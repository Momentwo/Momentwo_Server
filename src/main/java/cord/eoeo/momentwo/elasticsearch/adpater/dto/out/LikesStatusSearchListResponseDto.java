package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;


import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesStatusSearchListResponseDto {
    private List<LikesStatusSearchResponseDto> likesList;
    private long page;
    private long size;
    private long totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public LikesStatusSearchListResponseDto toDo(Page<LikesDocument> likesDocuments) {
        return new LikesStatusSearchListResponseDto(
                likesDocuments.stream().map(likesDocument -> new LikesStatusSearchResponseDto().toDo(likesDocument))
                        .collect(Collectors.toList()),
                likesDocuments.getNumber(),
                likesDocuments.getSize(),
                likesDocuments.getTotalPages(),
                likesDocuments.getTotalElements(),
                likesDocuments.hasNext(),
                likesDocuments.hasPrevious()
        );
    }
}
