package cord.eoeo.momentwo.member.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditGradeListRequestDto {
    @NotEmpty(message = "권한 변경을 위한 입력이 올바르지 않습니다.")
    private HashMap<String, String> editMemberList;
}
