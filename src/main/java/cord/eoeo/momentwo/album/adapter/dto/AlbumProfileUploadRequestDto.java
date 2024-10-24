package cord.eoeo.momentwo.album.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumProfileUploadRequestDto {
    private long albumId;
    private String filename;
}
