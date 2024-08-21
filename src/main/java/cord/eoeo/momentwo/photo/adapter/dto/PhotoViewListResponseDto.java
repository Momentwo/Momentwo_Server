package cord.eoeo.momentwo.photo.adapter.dto;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoViewListResponseDto {
    private List<PhotoViewResponseDto> photoList;

    public PhotoViewListResponseDto toDo(List<Photo> photoList) {
        return new PhotoViewListResponseDto(
                photoList.stream().map(photo -> new PhotoViewResponseDto().toDo(photo))
                        .collect(Collectors.toList())
        );
    }
}
