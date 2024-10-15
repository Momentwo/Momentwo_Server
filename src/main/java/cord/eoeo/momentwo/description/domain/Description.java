package cord.eoeo.momentwo.description.domain;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    @PrePersist
    public void init() {
        this.date = LocalDate.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "photoId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Photo photo;

    public Description(String description, Photo photo) {
        this.description = description;
        this.photo = photo;
    }
}
