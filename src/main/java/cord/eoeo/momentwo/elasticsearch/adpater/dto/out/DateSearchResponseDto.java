package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.DateDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateSearchResponseDto {
    private long photoId;
    private String photoImage;
    private LocalDate date;

    public DateSearchResponseDto toDo(DateDocument dateDocument) {
        return new DateSearchResponseDto(
                dateDocument.photoId,
                dateDocument.photoImage,
                dateDocument.date
        );
    }
}
