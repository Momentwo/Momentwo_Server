package cord.eoeo.momentwo.member.domain;

import cord.eoeo.momentwo.member.advice.exception.NotFoundGradeException;
import lombok.Getter;

import java.util.Arrays;

public enum MemberAlbumGrade {
    ROLE_ALBUM_ADMIN("ROLE_ALBUM_ADMIN", 1), // 관리자
    ROLE_ALBUM_SUB_ADMIN("ROLE_ALBUM_SUB_ADMIN", 2), // 부관리자
    ROLE_ALBUM_COMMON_MEMBER("ROLE_ALBUM_COMMON_MEMBER", 3); // 일반멤버

    private String albumGrade;

    @Getter
    private int priority;

    MemberAlbumGrade(String albumGrade, int priority) {
        this.albumGrade = albumGrade;
        this.priority = priority;
    }

    public static MemberAlbumGrade findMemberAlbumGrade(String keyCode) {
        return Arrays.stream(MemberAlbumGrade.values())
                .filter(albumGrade -> albumGrade.name().equals(keyCode))
                .findAny()
                .orElseThrow(NotFoundGradeException::new);
    }
}
