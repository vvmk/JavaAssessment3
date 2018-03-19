package user_management.security;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
    private final static int workload = 12;
    String hash;

    public Password(String password) {
        this.hash = hashPassword(password);
    }

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password_plaintext, salt);
    }

    public boolean matches(String password_plaintext) {
        return BCrypt.checkpw(password_plaintext, hash);
    }

    public String getHash() {
        return hash;
    }
}
