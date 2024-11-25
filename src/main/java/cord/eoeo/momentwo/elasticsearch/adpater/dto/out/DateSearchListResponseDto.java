package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.DateDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateSearchListResponseDto {
    private List<DateSearchResponseDto> datesList;
    private long page;
    private long size;
    private long totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public DateSearchListResponseDto toDo(Page<DateDocument> dateDocuments) {
        return new DateSearchListResponseDto(
                dateDocuments.stream().map(it -> new DateSearchResponseDto().toDo(it)).collect(Collectors.toList()),
                dateDocuments.getNumber(),
                dateDocuments.getSize(),
                dateDocuments.getTotalPages(),
                dateDocuments.getTotalElements(),
                dateDocuments.hasNext(),
                dateDocuments.hasPrevious()
        );
    }
}
