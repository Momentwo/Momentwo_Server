package cord.eoeo.momentwo.comment.domain;

import cord.eoeo.momentwo.photo.domain.Photo;
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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "photoId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Photo photo;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    @PrePersist
    public void init() {
        this.date = LocalDate.now();
    }

    public Comment(String comments, User user, Photo photo) {
        this.comments = comments;
        this.user = user;
        this.photo = photo;
    }
}
