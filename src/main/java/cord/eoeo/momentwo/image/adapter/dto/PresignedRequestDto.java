package cord.eoeo.momentwo.image.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresignedRequestDto {
    private long albumId;
    private String extension;
}
