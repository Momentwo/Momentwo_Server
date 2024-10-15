package cord.eoeo.momentwo.user.application.port.out;

public interface PasswordEncoder {
    String encoder(String password);
    boolean matches(String input, String database);
}
