package cord.eoeo.momentwo.image.adapter.dto;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageViewResponseDto {
    private long id;
    private String imageUrl;

    public ImageViewResponseDto toDo(Photo photo) {
        return new ImageViewResponseDto(photo.getId(), photo.getImageName());
    }
}
