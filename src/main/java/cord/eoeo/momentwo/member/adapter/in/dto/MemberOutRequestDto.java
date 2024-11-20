package cord.eoeo.momentwo.member.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberOutRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;
}
