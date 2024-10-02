package cord.eoeo.momentwo.album.adapter.dto;

import cord.eoeo.momentwo.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumRulesResponseDto {
    private long albumId;
    private String rules;

    public AlbumRulesResponseDto toDo(Member member) {
        return new AlbumRulesResponseDto(
                this.albumId = member.getAlbum().getId(),
                this.rules = member.getRules().toString()
        );
    }
}
