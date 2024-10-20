package cord.eoeo.momentwo.image.adapter.dto;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageViewListResponseDto {
    private List<ImageViewResponseDto> images;
    private long page;
    private long size;
    private long totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public ImageViewListResponseDto toDo(Page<Photo> photos) {
        return new ImageViewListResponseDto(
                photos.stream().map(photo -> new ImageViewResponseDto().toDo(photo)).collect(Collectors.toList()),
                photos.getNumber(),
                photos.getSize(),
                photos.getTotalPages(),
                photos.getTotalElements(),
                photos.hasNext(),
                photos.hasPrevious()
        );
    }
}
