package cord.eoeo.momentwo.subAlbum.adapter.dto;

import cord.eoeo.momentwo.image.adapter.dto.ImageViewResponseDto;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubAlbumResponseDto {
    private long id;
    private String subAlbumTitle;
    private List<ImageViewResponseDto> subTitleImageList;

    public SubAlbumResponseDto toDo(SubAlbum subAlbum, S3Client s3Client, String buckName, String imagePath) {
        return new SubAlbumResponseDto(
                subAlbum.getId(),
                subAlbum.getSubAlbumTitle(),
                subAlbum.getPhotos().stream()
                        .map(photo -> new ImageViewResponseDto()
                                .toDo(photo, s3Client.utilities().getUrl(b ->
                                        b.bucket(buckName).key(imagePath + photo.getImageName())).toString()))
                        .limit(4)
                        .collect(Collectors.toList())
        );
    }
}
