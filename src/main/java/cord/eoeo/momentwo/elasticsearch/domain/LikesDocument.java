package cord.eoeo.momentwo.elasticsearch.domain;

import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Document(indexName = "likes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesDocument {
    @Id
    private String id;

    @Field(type = FieldType.Long)
    private long photoId;

    @Field(type = FieldType.Keyword)
    private String nickname;

    @Field(type = FieldType.Long)
    private long albumId;

    @Field(type = FieldType.Long)
    private long subAlbumId;

    @Field(type = FieldType.Text)
    private String userProfileImage;

    public LikesDocument(User user, Photo photo) {
        this.id = photo.getId() + "_" + user.getNickname();
        this.photoId = photo.getId();
        this.nickname = user.getNickname();
        this.albumId = photo.getAlbum().getId();
        this.subAlbumId = photo.getSubAlbum().getId();
        this.userProfileImage = user.getUserProfileImage();
    }
}
