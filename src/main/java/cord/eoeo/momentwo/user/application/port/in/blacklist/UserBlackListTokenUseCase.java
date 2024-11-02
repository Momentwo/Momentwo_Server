package cord.eoeo.momentwo.user.application.port.in.blacklist;

public interface UserBlackListTokenUseCase {
    /**
     * 로그아웃 시 토큰 블랙리스트
     * @param token
     * token : 만료시킬 AT
     */
    void blackListToken(String token);
}
