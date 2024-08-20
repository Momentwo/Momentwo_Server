package cord.eoeo.momentwo.image.adapter.dto;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageViewResponseDto {
    private long id;
    private String imageUrl;

    @Value("${file.dir}")
    private String dir;

    public ImageViewResponseDto(long id, String url) {
        this.id = id;
        this.imageUrl = url;
    }

    public ImageViewResponseDto toDo(Photo photo) {
        return new ImageViewResponseDto(photo.getId(), dir + photo.getImageName());
    }
}
