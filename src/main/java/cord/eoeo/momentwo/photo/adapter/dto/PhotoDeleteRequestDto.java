package cord.eoeo.momentwo.photo.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDeleteRequestDto {
    private long albumId;
    private long subAlbumId;
    private List<Long> imagesId;
    private List<String> imagesFilename;
}
