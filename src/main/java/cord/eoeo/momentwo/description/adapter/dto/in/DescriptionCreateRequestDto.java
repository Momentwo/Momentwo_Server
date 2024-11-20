package cord.eoeo.momentwo.description.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionCreateRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "사진 아이디 누락")
    private Long photoId;

    @NotBlank(message = "설명 누락")
    private String description;
}
