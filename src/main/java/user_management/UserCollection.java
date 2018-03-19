package user_management;

import user_management.security.Password;
import user_management.security.UserAuthenticationFailedException;
import user_management.validation.InvalidEmailException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class UserCollection extends ArrayList<User> {

    public UserCollection() {
        super();
    }

    public UserCollection(int initialCapacity) {
        super(initialCapacity);
    }

    public UserCollection(Collection<? extends User> c) {
        super(c);
    }

    private static Field elementContainsField(String fieldName) {
        try {
            return User.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException nsfe) {
            return null;
        }
    }

    private static boolean fieldValuesMatch(Field checkField, Object objectWithValue, Object valueToMatch) {
        try {
            return checkField.get(objectWithValue).equals(valueToMatch);
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            return false;
        }
    }

    public User findById(int id) {
        try {
            return where("id", id).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findByEmail(String email) {
        try {
            return where("email", email).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User attemptLogin(String email, String password) throws UserAuthenticationFailedException {
        User u = findByEmail(email);
        if (u != null) {
            Password hash = new Password(password);
            if (u.getPassword().equals(hash)) {
                return u;
            } else
                throw new UserAuthenticationFailedException("Invalid Password");
        } else
            throw new UserAuthenticationFailedException("Email not found");
    }

    public int createUser(String name, String email, String password) {
        return 0;
    }

    public UserCollection where(String fieldName, Object value) {
        UserCollection result = new UserCollection();
        Field field = elementContainsField(fieldName);
        if (field != null) {
            for (User u : super.toArray(new User[0])) {
                if (fieldValuesMatch(field, u, value)) {
                    result.add(u);
                }
            }
        }
        return result;
    }
}
