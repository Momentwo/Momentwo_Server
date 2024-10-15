package cord.eoeo.momentwo.description.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionCreateRequestDto {
    private long albumId;
    private long photoId;
    private String description;
}
