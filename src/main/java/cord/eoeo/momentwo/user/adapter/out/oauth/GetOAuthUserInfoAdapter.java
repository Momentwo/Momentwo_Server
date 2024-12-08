package cord.eoeo.momentwo.user.adapter.out.oauth;

import cord.eoeo.momentwo.config.oauth.exception.CustomAuthenticationException;
import cord.eoeo.momentwo.elasticsearch.application.port.out.user.manager.UserSavePort;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindByOAuthInfoRepo;
import cord.eoeo.momentwo.user.application.port.out.oauth.GetOAuthUserInfoPort;
import cord.eoeo.momentwo.user.application.port.out.oauth.IsUsernameDuplicatePort;
import cord.eoeo.momentwo.user.domain.OAuthInfo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetOAuthUserInfoAdapter implements GetOAuthUserInfoPort {
    private final UserFindByOAuthInfoRepo userFindByOAuthInfoRepo;
    private final UserGenericRepo userGenericRepo;
    private final UserSavePort userSavePort;
    private final IsUsernameDuplicatePort isUsernameDuplicatePort;

    @Override
    public User userFindByOAuthInfo(String username, OAuthInfo oAuthInfo, Map<String, Object> attributes) {
        Optional<User> user = userFindByOAuthInfoRepo.userFindByOAuthInfo(username, oAuthInfo);

        if(user.isEmpty()) {
            if(isUsernameDuplicatePort.isUsernameDuplicate(username)) {
                throw new CustomAuthenticationException("가입된 이메일 정보가 존재합니다.");
            }
            User newUser = new User(
                    attributes.get(oAuthInfo.getName()).toString(),
                    username,
                    "",
                    "",
                    "",
                    "",
                    oAuthInfo
            );

            userGenericRepo.save(newUser);
            userSavePort.userSave(newUser);
            return newUser;
        }

        return user.get();
    }

    @Override
    public User userFindByOAuthInfo(OAuthInfo oAuthInfo, Map<String, Object> attributes, Map<String, Object> profiles) {
        String username = attributes.get(OAuthInfo.KAKAO.getIdentifier()).toString();
        Optional<User> user = userFindByOAuthInfoRepo.userFindByOAuthInfo(username, oAuthInfo);

        if(user.isEmpty()) {
            if(isUsernameDuplicatePort.isUsernameDuplicate(username)) {
                throw new CustomAuthenticationException("가입된 이메일 정보가 존재합니다.");
            }
            User newUser = new User(
                    profiles.get("nickname").toString(),
                    username,
                    "",
                    "",
                    "",
                    "",
                    oAuthInfo
            );

            userGenericRepo.save(newUser);
            userSavePort.userSave(newUser);
            return newUser;
        }

        return user.get();
    }
}
