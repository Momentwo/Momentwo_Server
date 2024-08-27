package cord.eoeo.momentwo.config.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FilePathConnect {
    // 이미지 http url 형태로 만들기 위해 소켓정보를 제공
    @Value("${file.dir}")
    private String dir;
}
