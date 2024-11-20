package cord.eoeo.momentwo.photo.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDeleteRequestDto {
    @NotBlank(message = "앨범 아이디 누락")
    private Long albumId;

    @NotBlank(message = "서브 앨범 아이디 누락")
    private Long subAlbumId;

    @NotEmpty(message = "이미지 아이디 누락")
    private List<Long> imagesId;

    @NotEmpty(message = "이미지 경로 누락")
    private List<String> imagesFilename;
}
