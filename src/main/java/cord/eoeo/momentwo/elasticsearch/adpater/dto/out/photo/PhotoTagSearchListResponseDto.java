package cord.eoeo.momentwo.elasticsearch.adpater.dto.out.photo;

import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoTagSearchListResponseDto {
    private List<PhotoTagSearchResponseDto> photoTagsSearch;
    private long page;
    private long size;
    private long totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public PhotoTagSearchListResponseDto toDo(Page<PhotoTagsDocument> photoTagsDocuments) {
        return new PhotoTagSearchListResponseDto(
                photoTagsDocuments.stream().map(photoTagsDocument -> new PhotoTagSearchResponseDto()
                        .toDo(photoTagsDocument)).collect(Collectors.toList()),
                photoTagsDocuments.getNumber(),
                photoTagsDocuments.getSize(),
                photoTagsDocuments.getTotalPages(),
                photoTagsDocuments.getTotalElements(),
                photoTagsDocuments.hasNext(),
                photoTagsDocuments.hasPrevious()
        );
    }
}
