package cord.eoeo.momentwo.image.application.port.out;

public interface MakeImagePresignedUrlPort {
    String makeImagePresignedUrl(String imageExtension, String path);
}
