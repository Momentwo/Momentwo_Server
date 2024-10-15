package cord.eoeo.momentwo.photo.domain;

import cord.eoeo.momentwo.album.domain.Album;
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
    @JoinColumn(nullable = false, name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "albumId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "subAlbumId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubAlbum subAlbum;

    public Photo(String imageName, String format, User user, Album album, SubAlbum subAlbum) {
        this.imageName = imageName;
        this.format = format;
        this.user = user;
        this.album = album;
        this.subAlbum = subAlbum;
    }
}
