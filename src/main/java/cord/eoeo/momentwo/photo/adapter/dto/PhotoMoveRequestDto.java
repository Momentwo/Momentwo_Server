package cord.eoeo.momentwo.photo.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoMoveRequestDto {
    private long albumId;
    private long moveSubAlbumId;
    private long photoId;
}
