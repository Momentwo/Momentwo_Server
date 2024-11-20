package cord.eoeo.momentwo.description.adapter.dto.out;

import cord.eoeo.momentwo.description.domain.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionResponseDto {
    private String nickname;
    private String userProfileImage;
    private String description;
    private LocalDate date;
    private List<String> photoTags;

    public DescriptionResponseDto toDo(Description description, List<String> photoTags) {
        return new DescriptionResponseDto(
                description.getPhoto().getUser().getNickname(),
                description.getPhoto().getUser().getUserProfileImage(),
                description.getDescription(),
                description.getDate(),
                photoTags
        );
    }
}
