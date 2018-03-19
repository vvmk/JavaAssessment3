package user_management.security;

public class UserAuthenticationFailedException extends Exception {
    public UserAuthenticationFailedException(String msg) {
        super(msg);
    }
}
