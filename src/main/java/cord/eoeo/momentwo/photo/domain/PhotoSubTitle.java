package cord.eoeo.momentwo.photo.domain;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotoSubTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String subTitle;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    @PrePersist
    void init() {
        this.date = LocalDate.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "subAlbum_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubAlbum subAlbum;

    @OneToMany(mappedBy = "photoSubTitle", fetch = FetchType.LAZY)
    private List<Photo> photos;

    public PhotoSubTitle(String subTitle, SubAlbum subAlbum) {
        this.subTitle = subTitle;
        this.subAlbum = subAlbum;
    }
}
