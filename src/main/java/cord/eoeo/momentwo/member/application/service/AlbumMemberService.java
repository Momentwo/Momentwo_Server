package cord.eoeo.momentwo.member.application.service;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.adapter.in.dto.AssignAdminRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.EditGradeListRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.InviteMembersRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.KickMembersRequestDto;
import cord.eoeo.momentwo.member.adapter.out.dto.AlbumMemberResponseDto;
import cord.eoeo.momentwo.member.advice.exception.*;
import cord.eoeo.momentwo.member.application.port.in.AlbumMemberUseCase;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberInvite;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberRepository;
import cord.eoeo.momentwo.member.application.port.out.GetAlbumInfo;
import cord.eoeo.momentwo.member.application.port.out.GetMemberInfo;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumMemberService implements AlbumMemberUseCase {
    private final AlbumMemberRepository albumMemberRepository;
    private final AlbumMemberInvite albumMemberInvite;
    private final GetAlbumInfo getAlbumInfo;
    private final GetMemberInfo getMemberInfo;
    private final GetAuthentication getAuthentication;

    @Override
    @Transactional
    public void inviteMembers(long albumId, InviteMembersRequestDto inviteMembersRequestDto) {
        Album album = getAlbumInfo.getAlbum(albumId).join();

        // 이미 앨범에 포함된 멤버
        List<String> existMember = getAlbumInfo.getAlbumMemberList(albumId);

        List<User> inviteList = new ArrayList<>();

        // 요청 보내기
        inviteMembersRequestDto.getInviteNicknames().forEach(nickname -> {
            User inviteUser = getMemberInfo.getUserInfoByNickname(nickname).join();
            inviteList.add(inviteUser);
        });

        inviteList.forEach(user -> {
            doInvite(album, user, existMember);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public AlbumMemberResponseDto getMembers(long albumId) {
        User user = getMemberInfo.getUserInfoByNickname(getAuthentication.getAuthentication().getName()).join();

        if(!getAlbumInfo.getAlbumMemberList(albumId).contains(user.getNickname())) {
            throw new NotFoundAccessException();
        }

        return new AlbumMemberResponseDto().toDo(
                albumMemberRepository.findMemberByAlbum(albumId)
        );
    }

    @Override
    @Transactional
    public void kickMembers(long albumId, KickMembersRequestDto kickMembersRequestDto) {
        User owner = getMemberInfo.getUserInfoByNickname(getAuthentication.getAuthentication().getName()).join();
        getAlbumInfo.checkAuthorityAdmin(albumId, owner.getId());

        // 자신 스스로 추방하려 할 경우
        if(kickMembersRequestDto.getKickMemberList().contains(owner.getNickname())) {
            throw new NotSelfKickException();
        }

        // 앨범 멤버에 포함되는지 확인
        kickMembersRequestDto.getKickMemberList().forEach(nickname -> {
            User kickedUser = getMemberInfo.getUserInfoByNickname(nickname).join();

            // 본인보다 낮은 권한만 추방 가능
            getAlbumInfo.doKickMember(albumId, owner, kickedUser);
        });

    }

    @Override
    @Transactional
    public void editMembersGrade(long albumId, EditGradeListRequestDto editGradeListRequestDto) {
        // 권한 변경자의 등급을 확인한다.
        User owner = getMemberInfo.getUserInfoByNickname(getAuthentication.getAuthentication().getName()).join();
        getAlbumInfo.checkAuthorityAdmin(albumId, owner.getId());

        // 호스트가 자신의 권한을 변경하려 할 경우
        if(editGradeListRequestDto.getEditMemberList().containsKey(owner.getNickname())) {
            throw new NotChangeSelfException();
        }

        // 리스트 안에 유저들이 바꿀 수 있는 값인지 확인 후
        // 권한을 변경시킨다.
        editGradeListRequestDto.getEditMemberList().forEach((nickname, rules) -> {
            User target = getMemberInfo.getUserInfoByNickname(nickname).join();
            getAlbumInfo.changeRules(albumId, owner, target, rules);
        });
    }

    @Override
    @Transactional
    public void assignAdmin(long albumId, AssignAdminRequestDto assignAdminRequestDto) {
        User owner = getMemberInfo.getUserInfoByNickname(getAuthentication.getAuthentication().getName()).join();

        // 관리자가 아닐경우
        if(!getAlbumInfo.getAlbumMemberInfo(albumId,
                owner.getId()).getRules().equals(MemberAlbumGrade.ROLE_ALBUM_ADMIN)) {
            throw new NotChangeAdminException();
        }
        User changeUser = getMemberInfo.getUserInfoByNickname(assignAdminRequestDto.getNickname()).join();
        // 관리자 -> 일반 멤버
        getAlbumInfo.assignAdmin(albumId, owner, MemberAlbumGrade.ROLE_ALBUM_COMMON_MEMBER);
        // 일반 멤버 -> 관리자
        getAlbumInfo.assignAdmin(albumId, changeUser, MemberAlbumGrade.ROLE_ALBUM_ADMIN);
    }

    @Override
    @Transactional
    public void outAlbum(long albumId) {
        User selfUser = getMemberInfo.getUserInfoByNickname(getAuthentication.getAuthentication().getName()).join();

        // 앨범 관리자가 나갈 경우 멤버가 존재한다면 나갈 수 없음
        // 하지만 멤버가 없을 경우 나갈 수 있음
        if(getAlbumInfo.getAlbumMemberInfo(albumId,
                selfUser.getId()).getRules().equals(MemberAlbumGrade.ROLE_ALBUM_ADMIN) &&
                !getAlbumInfo.getAlbumMemberList(albumId).isEmpty()) {
            throw new AdminAlbumOutException();
        }

        // 본인이 앨범에 속해있는지 확인한다.
        // 삭제 된 행이 있으면 true, 없으면 false 반환
        if(!getAlbumInfo.isAlbumOut(albumId, selfUser)) {
            throw new MemberNotInAlbumException();
        }
    }

    // 멤버 초대
    @Transactional(readOnly = true)
    private void doInvite(Album album, User inviteUser, List<String> existMember) {
        if(!existMember.contains(inviteUser.getNickname())) {
            albumMemberInvite.invite(album, inviteUser).join();
        }
    }
}
