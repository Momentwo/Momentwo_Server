package cord.eoeo.momentwo.like.adapter.dto.out;

import cord.eoeo.momentwo.like.domain.PhotoLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoLikesResponseDto {
    private long count;

    public PhotoLikesResponseDto toDo(PhotoLike photoLike) {
        return new PhotoLikesResponseDto(photoLike.getCount());
    }
}
