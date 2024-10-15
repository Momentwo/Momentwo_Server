package cord.eoeo.momentwo.config.security.jwt.port.out;

public interface JWTBlackList {
    void blackListToken(String token);

    boolean isTokenBlackListed(String token);
}
