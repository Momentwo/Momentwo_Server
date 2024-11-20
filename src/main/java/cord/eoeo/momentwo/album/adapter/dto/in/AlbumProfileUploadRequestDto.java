package cord.eoeo.momentwo.album.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumProfileUploadRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotBlank(message = "이미지 경로 누락")
    private String filename;
}
