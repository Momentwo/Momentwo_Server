package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.photo.application.port.in.PhotoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoUseCase photoUseCase;
}
