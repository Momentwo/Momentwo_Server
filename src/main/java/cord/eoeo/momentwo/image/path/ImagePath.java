package cord.eoeo.momentwo.image.path;

public enum ImagePath {
    SERVER_PROFILE_PATH(System.getProperty("user.home") + "\\Desktop\\momentwo\\profile\\"),
    SERVER_IMAGE_PATH(System.getProperty("user.home") + "\\Desktop\\momentwo\\image\\");

    private String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
