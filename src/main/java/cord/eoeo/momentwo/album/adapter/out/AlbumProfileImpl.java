package cord.eoeo.momentwo.album.adapter.out;

import cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin;
import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.application.port.out.AlbumProfile;
import cord.eoeo.momentwo.album.application.port.out.AlbumRepository;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.image.advice.exception.NotFoundImageException;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import cord.eoeo.momentwo.image.path.ImagePath;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class AlbumProfileImpl implements AlbumProfile {
    private final AlbumRepository albumRepository;
    private final AlbumManager albumManager;
    private final ImageManager imageManager;

    @Override
    @Transactional
    @CheckAlbumAdmin
    public void profileUpload(Member member, MultipartFile image) {
        try {
            Album album = member.getAlbum();

            // 이미지 업로드 후 저장된 이름 가져오기
            String newFilename = imageManager.imageUpload(image, ImagePath.SERVER_PROFILE_PATH.getPath()).get();
            // 이미지 삭제(현재) -> 기존 이미지가 없다면 업로드한 이미지만 저장, 기존 이미지가 있다면 삭제 진행
            imageManager.imageDelete(ImagePath.SERVER_PROFILE_PATH.getPath(), album.getProfileFilename());

            // 데이터 베이스에 이름 저장
            album.setProfileFilename(newFilename);
            albumRepository.save(album);
        } catch (Exception e) {
            throw new NotFoundImageException();
        }
    }

    @Override
    @Transactional
    @CheckAlbumAdmin
    public void profileDelete(Member member) {
        Album album = member.getAlbum();
        album.setProfileFilename(albumManager.getBaseImage());
        album.setProfileDate(LocalDate.now());
        albumRepository.save(album);
    }

    @Override
    @Transactional
    @CheckAlbumAdmin
    public void albumSubTitleEdit(Member member, String subTitle) {
        Album album = member.getAlbum();
        album.setSubTitle(subTitle);
        albumRepository.save(album);
    }

    @Override
    @Transactional
    @CheckAlbumAdmin
    public void albumSubTitleDelete(Member member) {
        Album album = member.getAlbum();
        album.setSubTitle("");
        albumRepository.save(album);
    }
}
