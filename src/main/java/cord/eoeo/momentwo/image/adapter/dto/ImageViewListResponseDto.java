package cord.eoeo.momentwo.image.adapter.dto;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageViewListResponseDto {
    private List<ImageViewResponseDto> images;

    public ImageViewListResponseDto toDo(List<Photo> photos) {
        return new ImageViewListResponseDto(
                photos.stream().map(photo -> new ImageViewResponseDto().toDo(photo))
                        .collect(Collectors.toList())
        );
    }
}
