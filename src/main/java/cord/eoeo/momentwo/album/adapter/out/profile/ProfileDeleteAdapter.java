package cord.eoeo.momentwo.album.adapter.out.profile;

import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumBaseImagePort;
import cord.eoeo.momentwo.album.application.port.out.profile.ProfileDeletePort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.application.port.out.ImageDeletePort;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ProfileDeleteAdapter implements ProfileDeletePort {
    private final ImageDeletePort imageDeletePort;
    private final S3Manager s3Manager;
    private final GetAlbumBaseImagePort getAlbumBaseImagePort;
    private final AlbumGenericRepo albumGenericRepo;

    @Override
    @Transactional
    public void profileDelete(Member member) {
        Album album = member.getAlbum();
        imageDeletePort.imageDelete(s3Manager.getProfileAlbumPath() + album.getProfileFilename()).join();
        // s3 스토리지 공간절약을 위해 삭제 시 앨범폴더엔 삭제
        // 대신 디폴트 폴더 사진 사용
        album.setProfileFilename(getAlbumBaseImagePort.getBaseImage());
        album.setProfileDate(LocalDate.now());
        albumGenericRepo.save(album);
    }
}
