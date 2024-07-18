package cord.eoeo.momentwo.friendship.domain;

import cord.eoeo.momentwo.user.domain.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "fromUser")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "toUser")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User toUser;

    @Column(nullable = false)
    public boolean accept;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate friendsDate;

    @PrePersist
    public void initDate() {
        this.friendsDate = LocalDate.now();
    }

    public Friendship(User fromUser, User toUser, boolean accept) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.accept = accept;
    }
}
