package cord.eoeo.momentwo.elasticsearch.adpater.dto.in.photo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoTagSearchRequestDto {
    private long albumId;
    private long photoId;
    private List<String> photoTags;
    private int page;
    private int size;
}
