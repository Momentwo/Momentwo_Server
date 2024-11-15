package cord.eoeo.momentwo.album.adapter.dto;

import cord.eoeo.momentwo.album.domain.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumInfoResponseDto {
    private long id;
    private String title;
    private String subTitle;
    private String profileImage;
    private long albumCount;

    public AlbumInfoResponseDto toDo(Album album, long albumCount) {
        return new AlbumInfoResponseDto(
                album.getId(),
                album.getTitle(),
                album.getSubTitle(),
                album.getProfileFilename(),
                albumCount
        );
    }
}
