package user_management.security;

import user_management.User;

public class Authenticator {
    public static boolean authenticate(User user, String passwordToCheck) {
        Password hash = new Password(passwordToCheck);
        return user.getPassword().equals(hash);
    }
}

