package cord.eoeo.momentwo.photo.domain;

import java.util.Arrays;

public enum PhotoFormat {
    FORMAT_TYPE_JPEG("JPEG"),
    FORMAT_TYPE_PNG("PNG"),
    FORMAT_TYPE_HEIC("HEIC");

    private String type;

    PhotoFormat(String type) {
        this.type = type;
    }

    public static PhotoFormat findPhotoType(String keyCode){
        return Arrays.stream(PhotoFormat.values())
                .filter(type -> type.getType().equals(keyCode))
                .findAny()
                .orElseThrow();
    }

    public String getType(){
        return type;
    }
}
