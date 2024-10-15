package cord.eoeo.momentwo.config.security.jwt.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;

    public TokenResponseDto toDo(String accessToken, String refreshToken) {
        return new TokenResponseDto(accessToken, refreshToken);
    }
}
