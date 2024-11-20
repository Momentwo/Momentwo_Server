package cord.eoeo.momentwo.image.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPresignedRequestDto {
    @NotBlank(message = "확장자 누락")
    private String extension;
}
