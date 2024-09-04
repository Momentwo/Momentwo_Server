package cord.eoeo.momentwo.member.adapter.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.advice.exception.NotFoundAccessException;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberJpaRepository;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberRepository;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlbumMemberRepositoryImpl implements AlbumMemberRepository {
    private final AlbumMemberJpaRepository albumMemberJpaRepository;

    @Override
    public void save(Member member) {
        albumMemberJpaRepository.save(member);
    }

    @Override
    public List<Member> findMemberByAlbum(long albumId) {
        return albumMemberJpaRepository.findMemberByAlbum(albumId);
    }

    @Override
    public Member findMemberGradeByAlbumIdAndUserId(long albumId, long userId) {
        return albumMemberJpaRepository.findMemberGradeByAlbumIdAndUserId(albumId, userId)
                .orElseThrow(NotFoundAccessException::new);
    }

    @Override
    public List<String> getAllNicknameList(long albumId) {
        return albumMemberJpaRepository.getAllNicknameList(albumId);
    }

    public void deleteById(long id) {
        albumMemberJpaRepository.deleteById(id);
    }

    @Override
    public int deleteByAlbumIdAndUserId(long albumId, long userId) {
        return albumMemberJpaRepository.deleteByAlbumIdAndUserId(albumId, userId);
    }

    @Override
    public List<Long> findAlbumIdByAdminUser(User user) {
        return albumMemberJpaRepository.findAlbumIdByAdminUser(user);
    }

    @Override
    public int getAlbumSize(User user) {
        return albumMemberJpaRepository.getAlbumSize(user);
    }

    @Override
    public List<Album> findAlbumByUser(User user) {
        return albumMemberJpaRepository.findAlbumByUser(user);
    }
}
