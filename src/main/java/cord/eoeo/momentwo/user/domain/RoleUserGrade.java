package cord.eoeo.momentwo.user.domain;

import java.util.Arrays;

public enum RoleUserGrade {
    ROLE_ADMIN("관리자"),
    ROLE_MANAGER("매니저"),
    ROLE_COMMON_MEMBER("일반회원");

    private String grade;

    RoleUserGrade(String grade) {
        this.grade = grade;
    }

    public static RoleUserGrade findUserGrade(String keyCode){
        return Arrays.stream(RoleUserGrade.values())
                .filter(roleUserGrade -> roleUserGrade.name().equals(keyCode))
                .findAny()
                .orElseThrow();
    }

    public String getGrade(){
        return grade;
    }
}
