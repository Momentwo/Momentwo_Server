package cord.eoeo.momentwo.album.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumCreateRequestDto {
    @NotBlank(message = "앨범 제목을 입력해주세요.")
    private String createTitle;
    private List<String> doInviteNicknameList;
}
