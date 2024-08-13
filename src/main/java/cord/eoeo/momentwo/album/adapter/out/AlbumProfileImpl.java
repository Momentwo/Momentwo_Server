package cord.eoeo.momentwo.album.adapter.out;

import cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin;
import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.application.port.out.AlbumProfile;
import cord.eoeo.momentwo.album.application.port.out.AlbumRepository;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class AlbumProfileImpl implements AlbumProfile {
    private final AlbumRepository albumRepository;
    private final AlbumManager albumManager;

    @SneakyThrows
    @Override
    @Transactional
    @CheckAlbumAdmin
    public void profileUpload(Member member, MultipartFile image) {
        Album album = member.getAlbum();

        // 파일 이름과 확장자를 분리
        String originalFilename = image.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // UUID 를 통한 고유한 이름 생성
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        // 서버 이미지 저장 경로
        String uploadDir = System.getProperty("user.home") + "\\Desktop\\momentwo\\profile\\";
        Path uploadPath = Paths.get(uploadDir);

        // 경로가 없다면 경로 생성
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 해당 이미지를 저장 경로에 복사
        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // 데이터 베이스에 이름 저장
        album.setProfileFilename(newFilename);
        albumRepository.save(album);
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
