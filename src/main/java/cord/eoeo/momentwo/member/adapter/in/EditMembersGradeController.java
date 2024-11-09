package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.adapter.in.dto.EditGradeListRequestDto;
import cord.eoeo.momentwo.member.application.port.in.EditMembersGradeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EditMembersGradeController {
    private EditMembersGradeUseCase editMembersGradeUseCase;

    // 멤버 권한 변경 (앨범 수정 권한)
    @PutMapping("/members/permission")
    @ResponseStatus(HttpStatus.OK)
    public void editMembersGrade(@RequestBody @Valid EditGradeListRequestDto editGradeListRequestDto) {
        editMembersGradeUseCase.editMembersGrade(editGradeListRequestDto);
    }
}
