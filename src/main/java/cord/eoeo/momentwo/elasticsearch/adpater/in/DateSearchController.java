package cord.eoeo.momentwo.elasticsearch.adpater.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.DateSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.DateSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.DateSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DateSearchController {
    private final DateSearchUseCase dateSearchUseCase;

    @GetMapping("/photo/date/search")
    @ResponseStatus(HttpStatus.OK)
    public DateSearchListResponseDto getDateSearch(@ModelAttribute @Valid DateSearchRequestDto dateSearchRequestDto) {
        return dateSearchUseCase.dateSearch(dateSearchRequestDto);
    }
}
