package cord.eoeo.momentwo.user.adapter.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempPasswordResponseDto {
    private String tempPassword;

    public TempPasswordResponseDto toDo(String tempPassword) {
        return new TempPasswordResponseDto(tempPassword);
    }
}
