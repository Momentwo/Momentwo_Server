package cord.eoeo.momentwo.elasticsearch.application.port.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.DateSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.DateSearchListResponseDto;

public interface DateSearchUseCase {
    DateSearchListResponseDto dateSearch(DateSearchRequestDto dateSearchRequestDto);
}
