package cord.eoeo.momentwo.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDate expirationDate;

    public RefreshToken(String token, String nickname, LocalDate expirationDate) {
        this.token = token;
        this.nickname = nickname;
        this.expirationDate = expirationDate;
    }
}
