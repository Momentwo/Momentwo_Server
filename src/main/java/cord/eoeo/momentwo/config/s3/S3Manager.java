package cord.eoeo.momentwo.config.s3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class S3Manager {
    @Value("${cloud.aws.s3.default-image}")
    private String profileBaseImage;

    @Value("${cloud.aws.s3.default-profile-image}")
    private String profileBaseImagePath;

    @Value("${cloud.aws.s3.images-path}")
    private String imagePath;

    @Value("${cloud.aws.s3.profiles-albums-path}")
    private String profileAlbumPath;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
}
