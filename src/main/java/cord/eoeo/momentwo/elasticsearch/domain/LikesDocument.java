package cord.eoeo.momentwo.elasticsearch.domain;

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

    @Field(type = FieldType.Keyword)
    private long photoId;

    @Field(type = FieldType.Keyword)
    private String nickname;

    public LikesDocument(User user, long photoId) {
        this.id = photoId + "_" + user.getNickname();
        this.photoId = photoId;
        this.nickname = user.getNickname();
    }
}
