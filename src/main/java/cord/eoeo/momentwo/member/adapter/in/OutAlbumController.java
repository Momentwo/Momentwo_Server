package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.adapter.in.dto.MemberOutRequestDto;
import cord.eoeo.momentwo.member.application.port.in.OutAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OutAlbumController {
    private final OutAlbumUseCase outAlbumUseCase;

    // 멤버 나가기 (개인)
    @DeleteMapping("/members/out")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void outAlbum(@ModelAttribute @Valid MemberOutRequestDto memberOutRequestDto) {
        outAlbumUseCase.outAlbum(memberOutRequestDto);
    }
}
