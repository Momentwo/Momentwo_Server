package cord.eoeo.momentwo.elasticsearch.advice.tag;

import cord.eoeo.momentwo.elasticsearch.advice.tag.exception.NotFoundAlbumTagsException;
import cord.eoeo.momentwo.elasticsearch.advice.tag.exception.NotFoundPhotoTagInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TagsExceptionHandler {
    @ExceptionHandler(NotFoundAlbumTagsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundAlbumTagsException() {
        return "앨범에 지정한 태그가 없습니다.";
    }

    @ExceptionHandler(NotFoundPhotoTagInfoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundPhotoTagInfoException() {
        return "앨범에 지정한 태그가 없습니다.";
    }
}
