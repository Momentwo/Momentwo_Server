package cord.eoeo.momentwo.photo.domain;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
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
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "name")
    private String imageName;

    @PrePersist
    public void initDate() {
        this.uploadDate = LocalDate.now();
    }

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate uploadDate;

    @Column(nullable = false)
    private String format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "subAlbum_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubAlbum subAlbum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "subTitle_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PhotoSubTitle photoSubTitle;

    public Photo(String imageName, String format, User user, SubAlbum subAlbum, PhotoSubTitle photoSubTitle) {
        this.imageName = imageName;
        this.format = format;
        this.user = user;
        this.subAlbum = subAlbum;
        this.photoSubTitle = photoSubTitle;
    }
}
