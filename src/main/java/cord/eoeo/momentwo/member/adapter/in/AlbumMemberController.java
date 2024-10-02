package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.adapter.in.dto.*;
import cord.eoeo.momentwo.member.adapter.out.dto.AlbumMemberResponseDto;
import cord.eoeo.momentwo.member.application.port.in.AlbumMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class AlbumMemberController {
    private final AlbumMemberUseCase albumMemberUseCase;

    // 멤버 초대
    @PostMapping("/invite")
    @ResponseStatus(HttpStatus.OK)
    public void inviteMembers(@RequestBody @Valid InviteMembersRequestDto inviteMembersRequestDto) {
        albumMemberUseCase.inviteMembers(inviteMembersRequestDto);
    }

    // 멤버 조회
    @GetMapping("/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumMemberResponseDto getMembers(@PathVariable long albumId) {
        return albumMemberUseCase.getMembers(albumId);
    }

    // 멤버 추방
    @DeleteMapping("/kick")
    @ResponseStatus(HttpStatus.OK)
    public void kickMembers(@RequestBody @Valid KickMembersRequestDto kickMembersRequestDto) {
        albumMemberUseCase.kickMembers(kickMembersRequestDto);
    }

    // 멤버 권한 변경 (앨범 수정 권한)
    @PutMapping("/permission")
    @ResponseStatus(HttpStatus.OK)
    public void editMembersGrade(@RequestBody @Valid EditGradeListRequestDto editGradeListRequestDto) {
        albumMemberUseCase.editMembersGrade(editGradeListRequestDto);
    }

    // 관리자 권한 넘겨주기
    @PutMapping("/assign/admin")
    @ResponseStatus(HttpStatus.OK)
    public void assignAdmin(@RequestBody @Valid AssignAdminRequestDto assignAdminRequestDto) {
        albumMemberUseCase.assignAdmin(assignAdminRequestDto);
    }

    // 멤버 나가기 (개인)
    @DeleteMapping("/out")
    @ResponseStatus(HttpStatus.OK)
    public void outAlbum(@ModelAttribute @Valid MemberOutRequestDto memberOutRequestDto) {
        albumMemberUseCase.outAlbum(memberOutRequestDto);
    }
}
