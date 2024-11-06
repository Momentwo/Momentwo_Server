package cord.eoeo.momentwo.member.application.service;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.adapter.in.dto.InviteMembersRequestDto;
import cord.eoeo.momentwo.member.application.port.in.InviteMembersUseCase;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberInvite;
import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByNicknamePort;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumMemberListPort;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InviteMembersService implements InviteMembersUseCase {
    private final GetAlbumPort getAlbumPort;
    private final GetAlbumMemberListPort getAlbumMemberListPort;
    private final GetUserInfoByNicknamePort getUserInfoByNicknamePort;
    private final AlbumMemberInvite albumMemberInvite;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void inviteMembers(InviteMembersRequestDto inviteMembersRequestDto) {
        Album album = getAlbumPort.getAlbum(inviteMembersRequestDto.getAlbumId()).join();

        // 이미 앨범에 포함된 멤버
        List<String> existMember = getAlbumMemberListPort.getAlbumMemberList(inviteMembersRequestDto.getAlbumId());

        List<User> inviteList = new ArrayList<>();

        // 요청 보내기
        inviteMembersRequestDto.getInviteNicknames().forEach(nickname -> {
            User inviteUser = getUserInfoByNicknamePort.getUserInfoByNickname(nickname).join();
            inviteList.add(inviteUser);
        });

        inviteList.forEach(user -> {
            doInvite(album, user, existMember);
        });
    }

    // 멤버 초대
    @Transactional(readOnly = true)
    private void doInvite(Album album, User inviteUser, List<String> existMember) {
        if(!existMember.contains(inviteUser.getNickname())) {
            albumMemberInvite.invite(album, inviteUser).join();
        }
    }
}
