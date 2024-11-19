package cord.eoeo.momentwo.elasticsearch.domain;

import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "photo_tags")
public class PhotoTagsDocument {
    @Id
    private String id;

    @Field(type = FieldType.Long)
    private long albumId;

    @Field(type = FieldType.Long)
    private long photoId;

    @Field(type = FieldType.Text)
    private String imageUrl;

    /**
     * 부분 검색
     * analyzer : 리스트 값을 하나의 토큰화하여 저장
     * ex) 저장된 데이터 "새로운", "여행"
     * 검색 시 "새로운 " or "새로운 여정"
     * 새로운이라는 단어가 포함된 것을 조회
     * 사용 : match
     */
    @Field(type = FieldType.Text, analyzer = "standard")
    private List<String> photoTagsMatches;

    /**
     * 정확한 일치 데이터 검색
     * ex) 저장된 데이터 "새로운", "여행"
     * 검색 시 ["새로운"] or ["새로운", "여행"]
     * 문자열안에 딱 해당 단어가 포함된 것만 조회
     * 사용 : terms
     */
    @Field(type = FieldType.Keyword)
    private List<String> photoTagsTerms;

    public PhotoTagsDocument(Photo photo, List<String> photoTags) {
        this.id = "tags_" + photo.getId();
        this.albumId = photo.getAlbum().getId();
        this.photoId = photo.getId();
        this.imageUrl = photo.getImageName();
        this.photoTagsMatches = photoTags;
        this.photoTagsTerms = photoTags;
    }
}
