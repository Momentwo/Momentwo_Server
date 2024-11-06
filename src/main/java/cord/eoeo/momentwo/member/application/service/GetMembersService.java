package cord.eoeo.momentwo.member.application.service;

import cord.eoeo.momentwo.member.adapter.out.dto.AlbumMemberResponseDto;
import cord.eoeo.momentwo.member.advice.exception.NotFoundAccessException;
import cord.eoeo.momentwo.member.application.port.in.GetMembersUseCase;
import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByNicknamePort;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindByAlbumRepo;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumMemberListPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetMembersService implements GetMembersUseCase {
    private final GetUserInfoByNicknamePort getUserInfoByNicknamePort;
    private final GetAuthentication getAuthentication;
    private final GetAlbumMemberListPort getAlbumMemberListPort;
    private final MemberFindByAlbumRepo memberFindByAlbumRepo;

    @Override
    @Transactional(readOnly = true)
    @CheckAlbumAccessRules
    public AlbumMemberResponseDto getMembers(long albumId) {
        User user = getUserInfoByNicknamePort.getUserInfoByNickname(getAuthentication.getAuthentication().getName())
                .join();

        if(!getAlbumMemberListPort.getAlbumMemberList(albumId).contains(user.getNickname())) {
            throw new NotFoundAccessException();
        }

        return new AlbumMemberResponseDto().toDo(
                memberFindByAlbumRepo.findMemberByAlbum(albumId)
        );
    }
}
