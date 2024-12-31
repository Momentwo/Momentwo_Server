package cord.eoeo.momentwo.friendship.domain;

import cord.eoeo.momentwo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "fromUser")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "toUser")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User toUser;

    @Column(nullable = false)
    private boolean accept;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate friendsDate;

    @PrePersist
    private void initDate() {
        this.friendsDate = LocalDate.now();
    }

    public Friendship(User fromUser, User toUser, boolean accept) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.accept = accept;
    }
}
