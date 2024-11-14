package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumInfoListResponseDto;
import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.port.in.GetAlbumUseCase;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindAlbumByUserRepo;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAlbumService implements GetAlbumUseCase {
    private final MemberFindAlbumByUserRepo memberFindAlbumByUserRepo;
    private final UserNicknameValidPort userNicknameValidPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional(readOnly = true)
    public AlbumInfoListResponseDto getAlbums() {
        User user = userNicknameValidPort.authenticationValid();

        List<Album> albums = memberFindAlbumByUserRepo.findAlbumByUser(user);
        if(albums.isEmpty()) {
            throw new NotFoundAlbumException();
        }
        return new AlbumInfoListResponseDto().toDo(albums);
    }
}
