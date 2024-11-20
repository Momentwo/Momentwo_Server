package cord.eoeo.momentwo.album.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumCreateRequestDto {
    @NotBlank(message = "앨범 제목을 입력해주세요.")
    private String createTitle;

    // 빈 리스트 입력도 가능 -> 생성 시 초대할 멤버가 없는 경우
    @NotNull(message = "빈 리스트로 입력")
    private List<String> doInviteNicknameList;

    @NotNull(message = "경로 또는 빈 문자열로 입력")
    private String filename;
}
