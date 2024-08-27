package cord.eoeo.momentwo.photo.adapter.dto.sub;

import cord.eoeo.momentwo.photo.domain.PhotoSubTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoSubTitleListResponseDto {
    private String subTitle;

    public PhotoSubTitleListResponseDto toDo(PhotoSubTitle photoSubTitle) {
        return new PhotoSubTitleListResponseDto(photoSubTitle.getSubTitle());
    }
}
