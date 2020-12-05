package edu.epam.bsuir.db.pool;

import java.sql.SQLException;

/**
 * This interface provides with ability
 * to get and release connections from
 * connection pool for usage.
 *
 * @author Maria Kartovitskaya
 */
public interface ConnectionPool {

    /**
     * This method retrieves {@code ConnectionProxy} from
     * a connection pool if it is available.
     *
     * @return {@code ConnectionProxy}.
     */
    ConnectionProxy retrieveConnection() throws SQLException;

    /**
     * This method releases {@code ConnectionProxy} back
     * to a connection pool.
     */
    void releaseConnection(ConnectionProxy connection);
}
