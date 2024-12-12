package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.adapter.in.dto.AssignAdminRequestDto;
import cord.eoeo.momentwo.member.application.port.in.AssignAdminUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AssignAdminController {
    private final AssignAdminUseCase assignAdminUseCase;

    // 관리자 권한 넘겨주기
    @PutMapping("/albums/members/assign/admin")
    @ResponseStatus(HttpStatus.OK)
    public void assignAdmin(@RequestBody @Valid AssignAdminRequestDto assignAdminRequestDto) {
        assignAdminUseCase.assignAdmin(assignAdminRequestDto);
    }
}
