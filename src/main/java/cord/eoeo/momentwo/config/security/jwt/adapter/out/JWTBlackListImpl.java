package cord.eoeo.momentwo.config.security.jwt.adapter.out;

import cord.eoeo.momentwo.config.security.jwt.port.out.BlackListRedisGenericRepo;
import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTBlackListImpl implements JWTBlackList {
    private final BlackListRedisGenericRepo blackListRedisGenericRepo;

    @Override
    public void blackListToken(String token) {
        blackListRedisGenericRepo.set(token, "expire");
        blackListRedisGenericRepo.expire(token, 30L);
    }

    // jwt 필터에서 로그아웃한 토큰이 있는지 확인
    @Override
    public boolean isTokenBlackListed(String token) {
        if(blackListRedisGenericRepo.get(token) != null) {
            return true;
        }
        return false;
    }
}
