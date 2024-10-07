package cord.eoeo.momentwo.image.adapter.dto;

import com.mysema.commons.lang.Pair;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public ImageViewListResponseDto toDo(Page<Photo> photos, List<URL> imagesUrl) {
        return new ImageViewListResponseDto(
                IntStream.range(0, photos.getSize())
                        .mapToObj(i -> Pair.of(photos.get().collect(Collectors.toList()).get(i), imagesUrl.get(i)))  // Pair로 묶기
                        .map(pair -> new ImageViewResponseDto().toDo(pair.getFirst(), pair.getSecond().toString()))  // 두 개의 값을 넘김
                        .collect(Collectors.toList()),
                photos.getNumber(),
                photos.getSize(),
                photos.getTotalPages(),
                photos.getTotalElements(),
                photos.hasNext(),
                photos.hasPrevious()
        );
    }
}
