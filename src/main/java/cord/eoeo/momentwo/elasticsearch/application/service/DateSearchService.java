package cord.eoeo.momentwo.elasticsearch.application.service;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.DateSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.DateSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.DateSearchUseCase;
import cord.eoeo.momentwo.elasticsearch.application.port.out.date.manager.GetPhotoDatePagingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DateSearchService implements DateSearchUseCase {
    private final GetPhotoDatePagingPort getPhotoDatePagingPort;

    @Override
    public DateSearchListResponseDto dateSearch(DateSearchRequestDto dateSearchRequestDto) {
        return new DateSearchListResponseDto()
                .toDo(getPhotoDatePagingPort.getPhotoDatePaging(
                        dateSearchRequestDto.getSubAlbumId(),
                        dateSearchRequestDto.getStartTime(),
                        dateSearchRequestDto.getEndTime(),
                        PageRequest.of(dateSearchRequestDto.getPage(), dateSearchRequestDto.getSize())
                ));
    }
}
