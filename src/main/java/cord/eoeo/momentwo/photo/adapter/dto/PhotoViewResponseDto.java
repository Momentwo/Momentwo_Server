package cord.eoeo.momentwo.photo.adapter.dto;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoViewResponseDto {
    private long id;
    private String imageUrl;

    public PhotoViewResponseDto toDo(Photo photo) {
        return new PhotoViewResponseDto(photo.getId(), photo.getImageName());
    }
}
