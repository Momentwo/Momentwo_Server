package cord.eoeo.momentwo.photo.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoUploadRequestDto {
    private long albumId;
    private long subAlbumId;
    private MultipartFile images;
}
