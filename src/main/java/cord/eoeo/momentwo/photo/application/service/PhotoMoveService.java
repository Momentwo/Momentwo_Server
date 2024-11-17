package cord.eoeo.momentwo.photo.application.service;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.LikesElasticSearchMovePhotoPort;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.IsLikesPort;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoMoveRequestDto;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.advice.exception.NotPhotoMoveException;
import cord.eoeo.momentwo.photo.application.port.in.PhotoMoveUseCase;
import cord.eoeo.momentwo.photo.application.port.out.MoveCheckAdminOrSelfPort;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.subAlbum.advice.exception.NotFoundSubAlbumException;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.out.get.GetSubAlbumInfoRepo;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoMoveService implements PhotoMoveUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final GetSubAlbumInfoRepo getSubAlbumInfoRepo;
    private final PhotoGenericRepo photoGenericRepo;
    private final MoveCheckAdminOrSelfPort moveCheckAdminOrSelfPort;
    private final IsLikesPort isLikesPort;
    private final LikesElasticSearchMovePhotoPort likesElasticSearchMovePhotoPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void photoMove(PhotoMoveRequestDto photoMoveRequestDto) {
        // 유저 정보 확인
        User user = userNicknameValidPort.authenticationValid();

        // 이동할 서브앨범이 존재하는지 확인
        SubAlbum moveSubAlbum = getSubAlbumInfoRepo.getSubAlbumInfo(photoMoveRequestDto.getMoveSubAlbumId())
                .orElseThrow(NotFoundSubAlbumException::new);
        if(moveSubAlbum.getAlbum().getId() != photoMoveRequestDto.getAlbumId()) {
            throw new NotPhotoMoveException();
        }

        // 본인이 올린 사진 or 관리자가 옮길 수 있게?
        Photo savePhoto = photoGenericRepo.findById(photoMoveRequestDto.getPhotoId())
                .orElseThrow(NotFoundPhotoException::new);
        moveCheckAdminOrSelfPort.moveCheckAdminOrSelf(savePhoto, user);

        // 같은 서브 앨범이라면 예외처리
        if(savePhoto.getSubAlbum().getId() == photoMoveRequestDto.getMoveSubAlbumId()) {
            throw new NotPhotoMoveException();
        }

        // 수정 정보 저장
        savePhoto.setSubAlbum(moveSubAlbum);
        photoGenericRepo.save(savePhoto);

        // 검색엔진에 저장된 정보 수정
        // 좋아요가 있었다면 서브앨범 정보만 변경
        if(isLikesPort.isLikes(savePhoto.getUser(), savePhoto.getId())) {
            likesElasticSearchMovePhotoPort.editSubAlbumId(
                    savePhoto,
                    user,
                    photoMoveRequestDto.getMoveSubAlbumId()
            );
        }
    }
}
