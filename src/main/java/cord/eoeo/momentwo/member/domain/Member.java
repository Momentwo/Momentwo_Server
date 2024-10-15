package cord.eoeo.momentwo.member.domain;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate joinDate;

    @PrePersist
    public void initDate() {
        this.joinDate = LocalDate.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "albumId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album album;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberAlbumGrade rules;

    public Member(User user, Album album, MemberAlbumGrade rules) {
        this.user = user;
        this.album = album;
        this.rules = rules;
    }
}
