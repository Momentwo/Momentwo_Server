package cord.eoeo.momentwo.image.application.port.out;

import java.util.concurrent.CompletableFuture;

public interface ImageDeletePort {
    CompletableFuture<Void> imageDelete(String filename);
}
