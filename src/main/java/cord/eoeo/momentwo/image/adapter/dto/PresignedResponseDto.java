package cord.eoeo.momentwo.image.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresignedResponseDto {
    private String presignedUrl;

    public PresignedResponseDto toDo(String url) {
        return new PresignedResponseDto(
                this.presignedUrl = url
        );
    }
}
