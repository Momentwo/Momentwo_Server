package cord.eoeo.momentwo.photo.adapter.out;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cord.eoeo.momentwo.photo.application.port.out.PhotoPageRepository;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.photo.domain.QPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PhotoPageRepositoryImpl implements PhotoPageRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Photo> findQPhotoBySubAlbumIdCustomPaging(long subAlbumId, Pageable pageable, long cursorId) {
        QPhoto photo = QPhoto.photo;

        // 전체 갯수 카운트 (안하면 DTO 에서 표시가 안됌 -> fetchCount 대신 사용)
        Long photoCount = jpaQueryFactory
                .select(photo.count())
                .from(photo)
                .where(photo.subAlbum.id.eq(subAlbumId))
                .fetchOne();

        List<Photo> photos = jpaQueryFactory
                .select(photo)
                .from(photo)
                .where(photo.subAlbum.id.eq(subAlbumId).and(cursor(cursorId, photo)))
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(photos, pageable, photoCount);
    }

    public BooleanExpression cursor(Long cursorId, QPhoto photo) {
        if(cursorId == null) {
            return null;
        }
        return photo.id.gt(cursorId);
    }
}
