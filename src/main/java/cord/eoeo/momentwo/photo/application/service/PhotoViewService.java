package cord.eoeo.momentwo.photo.application.service;

import cord.eoeo.momentwo.config.page.CursorPage;
import cord.eoeo.momentwo.image.adapter.dto.ImageViewListResponseDto;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.in.PhotoViewUseCase;
import cord.eoeo.momentwo.photo.application.port.out.PhotoPageRepository;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoViewService implements PhotoViewUseCase {
    private final PhotoPageRepository photoPageRepository;

    @Override
    @Transactional(readOnly = true)
    @CheckAlbumAccessRules
    public ImageViewListResponseDto photoView(long albumId, long subAlbumId, int size, long cursor) {
        CursorPage<Photo> photoList = photoPageRepository
                .findQPhotoBySubAlbumIdCustomPaging(
                        subAlbumId,
                        PageRequest.of((int) (cursor / size), size),
                        cursor
                );

        return new ImageViewListResponseDto().toDo(photoList);
    }
}
