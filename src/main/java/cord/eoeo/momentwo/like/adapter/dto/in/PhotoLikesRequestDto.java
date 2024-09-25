package cord.eoeo.momentwo.like.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoLikesRequestDto {
    private long albumId;
    private long photoId;
}
