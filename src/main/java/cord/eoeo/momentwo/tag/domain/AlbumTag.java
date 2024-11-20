package cord.eoeo.momentwo.tag.domain;

import cord.eoeo.momentwo.album.domain.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "album")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album album;

    @Column(nullable = false, name = "tag")
    private String tag;

    public AlbumTag(Album album, String tag) {
        this.album = album;
        this.tag = tag;
    }
}
