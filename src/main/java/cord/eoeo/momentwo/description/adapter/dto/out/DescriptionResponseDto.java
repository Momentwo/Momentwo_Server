package cord.eoeo.momentwo.description.adapter.dto.out;

import cord.eoeo.momentwo.description.domain.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionResponseDto {
    private String nickname;
    private String description;
    private LocalDate date;

    public DescriptionResponseDto toDo(Description description) {
        return new DescriptionResponseDto(
                description.getPhoto().getUser().getNickname(),
                description.getDescription(),
                description.getDate()
        );
    }
}
