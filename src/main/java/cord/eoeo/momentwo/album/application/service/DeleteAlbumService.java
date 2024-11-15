package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumDeleteRequestDto;
import cord.eoeo.momentwo.album.advice.exception.NotDeleteAlbumException;
import cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin;
import cord.eoeo.momentwo.album.application.port.in.DeleteAlbumUseCase;
import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.album.application.port.out.manager.AlbumS3ImageDeletePort;
import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumAdminPort;
import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumOneMemberPort;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAlbumService implements DeleteAlbumUseCase {
    private final IsCheckAlbumAdminPort isCheckAlbumAdminPort;
    private final IsCheckAlbumOneMemberPort isCheckAlbumOneMemberPort;
    private final GetAlbumMemberInfoPort getAlbumMemberInfoPort;
    private final AlbumGenericRepo albumGenericRepo;
    private final AlbumS3ImageDeletePort albumS3ImageDeletePort;

    @Transactional
    @Override
    @CheckAlbumAdmin
    public void deleteAlbums(AlbumDeleteRequestDto albumDeleteRequestDto) {
        Member member = getAlbumMemberInfoPort.getMemberInfo(albumDeleteRequestDto.getAlbumId());
        // 관리자이면서 앨범 속 멤버가 한명 이상인 경우 예외
        if(isCheckAlbumAdminPort.isCheckAlbumAdmin(member)
                && !isCheckAlbumOneMemberPort.isCheckAlbumOneMember(albumDeleteRequestDto.getAlbumId())) {
            throw new NotDeleteAlbumException();
        }

        // S3 저장소에 있는 사진 삭제
        albumS3ImageDeletePort.s3ImageDelete(albumDeleteRequestDto.getAlbumId());

        // 앨범에 연관된 데이터 다 삭제
        albumGenericRepo.deleteById(member.getAlbum().getId());
    }
}
