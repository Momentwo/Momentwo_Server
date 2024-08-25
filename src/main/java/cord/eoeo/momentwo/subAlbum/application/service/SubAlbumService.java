package cord.eoeo.momentwo.subAlbum.application.service;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.advice.exception.NotFoundAccessException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRulesCheck;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;
import cord.eoeo.momentwo.subAlbum.application.port.in.SubAlbumUseCase;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumRepository;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubAlbumService implements SubAlbumUseCase {
    private final AlbumManager albumManager;
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;
    private final PhotoRulesCheck rulesCheck;
    private final SubAlbumRepository subAlbumRepository;

    @Override
    public void createSubAlbum(SubAlbumCreateRequestDto subAlbumCreateRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        Album album = albumManager.getAlbumInfo(subAlbumCreateRequestDto.getAlbumId());

        if(!rulesCheck.isAlbumMember(album.getId(), user)) {
            throw new NotFoundAccessException();
        }

        SubAlbum newSubAlbum = new SubAlbum(subAlbumCreateRequestDto.getTitle(), album);
        subAlbumRepository.save(newSubAlbum);
    }
}
