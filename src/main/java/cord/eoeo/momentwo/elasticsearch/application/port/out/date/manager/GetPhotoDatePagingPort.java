package cord.eoeo.momentwo.elasticsearch.application.port.out.date.manager;

import cord.eoeo.momentwo.elasticsearch.domain.DateDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface GetPhotoDatePagingPort {
    Page<DateDocument> getPhotoDatePaging(long subAlbumId, LocalDate startTime, LocalDate endTime, Pageable pageable);
}
