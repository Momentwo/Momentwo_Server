package cord.eoeo.momentwo.elasticsearch.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@Document(indexName = "friends")
public class FriendsDocument {
    @Id
    private long id;

    @Field(type = FieldType.Keyword)
    private String nickname; // 친구 요청을 받은 사람

    public FriendsDocument(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
