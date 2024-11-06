package cord.eoeo.momentwo.member.adapter.out;

import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericJpaRepo;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericRepo;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlbumMemberGenericAdapter implements AlbumMemberGenericRepo {
    private final AlbumMemberGenericJpaRepo albumMemberGenericJpaRepo;

    @Override
    public void save(Member entity) {
        albumMemberGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return albumMemberGenericJpaRepo.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return albumMemberGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        albumMemberGenericJpaRepo.deleteById(id);
    }
}
