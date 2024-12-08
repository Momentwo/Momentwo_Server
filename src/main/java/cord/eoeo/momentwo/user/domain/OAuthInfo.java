package cord.eoeo.momentwo.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum OAuthInfo {
    GOOGLE(null, "sub", "email", "name"),
    KAKAO("kakao_account", "id", "email", "name");

    private final String key;
    private final String code;
    private final String identifier;
    private final String name;

    public static OAuthInfo from(String name) {
        String upperName = name.toUpperCase();
        return Arrays.stream(OAuthInfo.values())
                .filter(it -> it.name().equals(upperName))
                .findFirst()
                .orElseThrow();
    }
}
