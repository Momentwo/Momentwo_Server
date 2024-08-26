package cord.eoeo.momentwo.subAlbum.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubAlbumDeleteRequestDto {
    private long albumId;
    private List<Long> subAlbumIds;
}
