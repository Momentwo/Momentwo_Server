package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumDeleteRequestDto;
import cord.eoeo.momentwo.album.advice.exception.NotDeleteAlbumException;
import cord.eoeo.momentwo.album.application.port.in.DeleteAlbumUseCase;
import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.member.application.port.out.GetAlbumInfo;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAlbumService implements DeleteAlbumUseCase {
    private final GetAlbumInfo getAlbumInfo;
    private final GetAlbumMemberInfoPort getAlbumMemberInfoPort;
    private final AlbumGenericRepo albumGenericRepo;

    @Transactional
    @Override
    public void deleteAlbums(AlbumDeleteRequestDto albumDeleteRequestDto) {
        Member member = getAlbumMemberInfoPort.getMemberInfo(albumDeleteRequestDto.getAlbumId());
        // 관리자이면서 앨범 속 멤버가 한명 이상인 경우 예외
        if(getAlbumInfo.isCheckAlbumAdmin(member)
                && !getAlbumInfo.isCheckAlbumOneMember(albumDeleteRequestDto.getAlbumId())) {
            throw new NotDeleteAlbumException();
        }
        albumGenericRepo.deleteById(member.getAlbum().getId());
    }
}
