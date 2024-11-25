package cord.eoeo.momentwo.elasticsearch.domain;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.time.LocalDate;

@Document(indexName = "photo_date")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateDocument {
    @Id
    public String id;

    @Field(type = FieldType.Long)
    public long subAlbumId;

    @Field(type = FieldType.Long)
    public long photoId;

    @Field(type = FieldType.Text)
    public String photoImage;

    @Field(type = FieldType.Date)
    public LocalDate date;

    public DateDocument(Photo photo) {
        this.id = "date_" + photo.getId();
        this.photoId = photo.getId();
        this.subAlbumId = photo.getSubAlbum().getId();
        this.photoImage = photo.getImageName();
        this.date = photo.getUploadDate();
    }
}
