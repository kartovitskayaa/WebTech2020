package edu.epam.bsuir.service.util;

import edu.epam.bsuir.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationUtil {

    private AuthenticationUtil() {
    }

    public static String getHash(String password) throws ServiceException {
        if (password == null || password.isEmpty()) {
            throw new ServiceException("Validation error: password is empty");
        }

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) throws ServiceException {
        if (password == null || password.isEmpty() || hashedPassword == null || hashedPassword.isEmpty()) {
            throw new ServiceException("Validation error: password is empty");
        }

        return BCrypt.checkpw(password, hashedPassword);
    }
}
