package cord.eoeo.momentwo.elasticsearch.domain;

import cord.eoeo.momentwo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "users")
public class UserDocument {
    @Id
    private long id;

    @Field(type = FieldType.Keyword)
    private String nickname;

    @Field(type = FieldType.Text)
    private String userProfileImage;

    public UserDocument(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.userProfileImage = user.getUserProfileImage();
    }
}
