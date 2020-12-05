package edu.epam.bsuir.dao.exception;

/**
 * This class is an {@code Exception} class for DAO layer,
 * in which all other exceptions will be wrapped.
 *
 * @author Maria Kartovitskaya
 */
public class DAOException extends Exception {

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
