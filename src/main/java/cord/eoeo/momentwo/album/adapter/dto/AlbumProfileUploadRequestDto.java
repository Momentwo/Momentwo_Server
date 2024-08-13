package cord.eoeo.momentwo.album.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumProfileUploadRequestDto {
    private long albumId;
    private MultipartFile profileImage;
}
