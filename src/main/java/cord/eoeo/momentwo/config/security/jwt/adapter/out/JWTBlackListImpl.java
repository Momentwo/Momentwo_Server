package cord.eoeo.momentwo.config.security.jwt.adapter.out;

import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class JWTBlackListImpl implements JWTBlackList {
    private Map<String, Long> tokenBlackList = new HashMap<>();

    @Override
    public void blackListToken(String token) {
        tokenBlackList.put(token, System.currentTimeMillis());
    }

    // jwt 필터에서 로그아웃한 토큰이 있는지 확인
    @Override
    public boolean isTokenBlackListed(String token) {
        return tokenBlackList.containsKey(token);
    }

    // 레디스 쓰기전까진 스케줄러를 통해 로그아웃 토큰 정리
    @Scheduled(fixedRate = 3600000)
    public void removeExpiredTokens() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<String, Long>> iterator = tokenBlackList.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            if(now - entry.getValue() > 3600000) {
                iterator.remove();
            }
        }
    }
}
