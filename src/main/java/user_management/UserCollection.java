package user_management;

import org.apache.commons.validator.routines.EmailValidator;
import user_management.security.Authenticator;
import user_management.security.UserAuthenticationFailedException;
import user_management.validation.EmailNotAvailableException;
import user_management.validation.InvalidEmailException;
import user_management.validation.PasswordTooSimpleException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

    public User findById(int id) {
        Iterator iterator = super.iterator();
        while (iterator.hasNext()) {
            User u = (User) iterator.next();
            if (u.getId() == id)
                return u;
        }
        return null;
    }

    public User findByEmail(String email) {
        Iterator iterator = super.iterator();
        while (iterator.hasNext()) {
            User u = (User) iterator.next();
            if (u.getEmail().equalsIgnoreCase(email))
                return u;
        }
        return null;
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
        for (int i = 1; ; i++) {
            if (findById(i) == null)
                return i;
        }
    }
}
