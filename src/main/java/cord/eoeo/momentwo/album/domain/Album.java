package cord.eoeo.momentwo.album.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;

    @PrePersist
    private void initDate() {
        this.createDate = LocalDate.now();
    }

    public Album(String title){
        this.title = title;
    }
}
