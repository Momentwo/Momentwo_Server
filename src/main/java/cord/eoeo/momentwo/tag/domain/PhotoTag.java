package cord.eoeo.momentwo.tag.domain;

import cord.eoeo.momentwo.photo.domain.Photo;
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
public class PhotoTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "photo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Photo photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "albumTag")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AlbumTag albumTag;

    public PhotoTag(Photo photo, AlbumTag albumTag) {
        this.photo = photo;
        this.albumTag = albumTag;
    }
}
