package cord.eoeo.momentwo.image.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDownLoadResponseDto {
    private String imageUrl;

    public ImageDownLoadResponseDto toDo(String imageUrl) {
        return new ImageDownLoadResponseDto(imageUrl);
    }
}
