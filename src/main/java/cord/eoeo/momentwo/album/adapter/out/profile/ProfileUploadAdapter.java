package cord.eoeo.momentwo.album.adapter.out.profile;

import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.profile.ProfileUploadPort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.advice.exception.NotFoundImageException;
import cord.eoeo.momentwo.image.application.port.out.ImageDeletePort;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProfileUploadAdapter implements ProfileUploadPort {
    private final ImageDeletePort imageDeletePort;
    private final S3Manager s3Manager;
    private final AlbumGenericRepo albumGenericRepo;

    @Override
    @Transactional
    public void profileUpload(Member member, String filename) {
        try {
            Album album = member.getAlbum();

            // 이미지 삭제(현재) -> 기존 이미지가 없다면 업로드한 이미지만 저장, 기존 이미지가 있다면 삭제 진행
            imageDeletePort.imageDelete(s3Manager.getProfileAlbumPath() + album.getProfileFilename()).join();

            // 데이터 베이스에 이름 저장
            album.setProfileFilename(filename);
            albumGenericRepo.save(album);
        } catch (Exception e) {
            throw new NotFoundImageException();
        }
    }
}
