package cord.eoeo.momentwo.config.page;

import lombok.Getter;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Getter
public class CursorPage<T> extends PageImpl<T> {
    private final Object nextCursor;

    public CursorPage(List<T> content, Object nextCursor) {
        super(content);
        this.nextCursor = nextCursor;
    }
}
