package cord.eoeo.momentwo.user.adapter.out.oauth;

import cord.eoeo.momentwo.user.advice.exception.NotFoundOAuthInfoTypeException;
import cord.eoeo.momentwo.user.application.port.out.oauth.OAuth2UserInfo;
import cord.eoeo.momentwo.user.application.port.out.oauth.OAuth2UserInfoFactoryPort;
import cord.eoeo.momentwo.user.domain.OAuthInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OAuth2UserInfoFactoryAdapter implements OAuth2UserInfoFactoryPort {

    @Override
    public OAuth2UserInfo getOAuth2UserInfo(OAuthInfo oAuthInfo, Map<String, Object> attributes) {
        switch (oAuthInfo) {
            case GOOGLE:
                return new GoogleOAuth2UserInfoAdapter(attributes);

            case KAKAO:
                return new KakaoOAuth2UserInfoAdapter(attributes);
        }
        throw new NotFoundOAuthInfoTypeException();
    }
}
