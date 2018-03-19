package user_management;

import org.apache.commons.validator.routines.EmailValidator;
import user_management.security.Authenticator;
import user_management.security.UserAuthenticationFailedException;
import user_management.validation.EmailNotAvailableException;
import user_management.validation.InvalidEmailException;
import user_management.validation.PasswordTooSimpleException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public class UserCollection extends ArrayList<User> {
    EmailValidator validator = EmailValidator.getInstance();
    private int lastId;

    public UserCollection() {
        super();
        lastId = 0;
    }

    public UserCollection(int initialCapacity) {
        super(initialCapacity);
        lastId = 0;
    }

    public UserCollection(Collection<? extends User> c) {
        super(c);
        lastId = c.size();
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
            if (Authenticator.authenticate(u, password)) {
                return u;
            } else
                throw new UserAuthenticationFailedException("Invalid Password");
        } else
            throw new UserAuthenticationFailedException("Email not found");
    }

    public int createUser(String name, String email, String password) throws EmailNotAvailableException, InvalidEmailException, PasswordTooSimpleException {
        if (findByEmail(email) != null)
            throw new EmailNotAvailableException();

        if (!validator.isValid(email))
            throw new InvalidEmailException();

        if (!validPassword(password))
            throw new PasswordTooSimpleException();

        int nextId = getNextAvailableIdAndIncrement();
        add(new User(nextId, name, email, password));

        return nextId;
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

    /**
     * The password provided has less than 8 characters
     * The password provided has no upper case letters
     * The password provided has no lower case letters
     * The password provided has no numbers
     * The password provided has none of the following characters: !@#$%^&*()<>?
     */
    private boolean validPassword(String plaintext_password) {
        Pattern p = Pattern.compile("^.*(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()<>?]).*$");
        return p.matcher(plaintext_password).matches();
    }

    public int getNextAvailableIdAndIncrement() {
        return ++lastId;
    }
}
