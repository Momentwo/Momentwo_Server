package cord.eoeo.momentwo.subAlbum.domain;

import cord.eoeo.momentwo.album.domain.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String subAlbumTitle;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    @PrePersist
    private void init() {
        this.date = LocalDate.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "album_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album album;

    public SubAlbum(String subAlbumTitle, Album album) {
        this.subAlbumTitle = subAlbumTitle;
        this.album = album;
    }
}
