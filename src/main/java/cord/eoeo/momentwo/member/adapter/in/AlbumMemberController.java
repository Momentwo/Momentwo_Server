package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.adapter.in.dto.AssignAdminRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.EditGradeListRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.InviteMembersRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.KickMembersRequestDto;
import cord.eoeo.momentwo.member.adapter.out.dto.AlbumMemberResponseDto;
import cord.eoeo.momentwo.member.application.port.in.AlbumMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumMemberController {
    private final AlbumMemberUseCase albumMemberUseCase;

    // 멤버 초대
    @PostMapping("/{albumId}/members/invite")
    @ResponseStatus(HttpStatus.OK)
    public void inviteMembers(@PathVariable long albumId,
                             @RequestBody @Valid InviteMembersRequestDto inviteMembersRequestDto) {
        albumMemberUseCase.inviteMembers(albumId, inviteMembersRequestDto);
    }

    // 멤버 조회
    @GetMapping("/{albumId}/members")
    @ResponseStatus(HttpStatus.OK)
    public AlbumMemberResponseDto getMembers(@PathVariable long albumId) {
        return albumMemberUseCase.getMembers(albumId);
    }

    // 멤버 추방
    @DeleteMapping("/{albumId}/members/kick")
    @ResponseStatus(HttpStatus.OK)
    public void kickMembers(@PathVariable long albumId,
                           @RequestBody @Valid KickMembersRequestDto kickMembersRequestDto) {
        albumMemberUseCase.kickMembers(albumId, kickMembersRequestDto);
    }

    // 멤버 권한 변경 (앨범 수정 권한)
    @PutMapping("/{albumId}/members/permission")
    @ResponseStatus(HttpStatus.OK)
    public void editMembersGrade(@PathVariable long albumId,
                                                    @RequestBody @Valid EditGradeListRequestDto editGradeListRequestDto) {
        albumMemberUseCase.editMembersGrade(albumId, editGradeListRequestDto);
    }

    // 관리자 권한 넘겨주기
    @PutMapping("/{albumId}/members/assign/admin")
    public void assignAdmin(@PathVariable long albumId,
                            @RequestBody @Valid AssignAdminRequestDto assignAdminRequestDto) {
        albumMemberUseCase.assignAdmin(albumId, assignAdminRequestDto);
    }

    // 멤버 나가기 (개인)
    @DeleteMapping("/{albumId}/members/out")
    public void outAlbum(@PathVariable long albumId) {
        albumMemberUseCase.outAlbum(albumId);
    }
}
