package edu.epam.bsuir.db.pool.impl;

import edu.epam.bsuir.config.DBConfig;
import edu.epam.bsuir.db.pool.ConnectionPool;
import edu.epam.bsuir.db.pool.ConnectionProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This {@code ElectiveConnectionPool} class provides with ability
 * to get and release connections from
 * connection pool for usage.
 *
 * For JavaDoc see {@link ConnectionPool} and {@link DBConfig}.
 *
 * @author Maria Kartovitskaya
 */
public class ElectiveConnectionPool implements ConnectionPool {

    public static final ElectiveConnectionPool CONNECTION_POOL_INSTANCE = new ElectiveConnectionPool();

    private final DBConfig configuration = DBConfig.getInstance();

    private final List<ConnectionProxy> availableConnections = new ArrayList<>(configuration.getInitPoolSize());
    private final List<ConnectionProxy> takenConnections = new ArrayList<>();

    private ElectiveConnectionPool() {
        try {
            initConnectionPool();
        } catch (SQLException e) {
            System.exit(1);
        }
    }

    private void initConnectionPool() throws SQLException {
        createConnections(configuration.getInitPoolSize());
    }

    @Override
    public ConnectionProxy retrieveConnection() throws SQLException {
        ConnectionProxy connection;
        if (availableConnections.isEmpty() && takenConnections.size() < configuration.getMaxPoolSize()) {
            createConnections(configuration.getPoolIncreaseStep());
        }

        connection = availableConnections.get(0);

        if (Objects.nonNull(connection)) {
            availableConnections.remove(0);
            takenConnections.add(connection);
        }

        return connection;
    }

    @Override
    public void releaseConnection(ConnectionProxy connection) {
        takenConnections.remove(connection);
        availableConnections.add(connection);
    }

    public void closeAll() throws SQLException {
        if (!takenConnections.isEmpty()) {
            availableConnections.addAll(takenConnections);
        }

        if (!availableConnections.isEmpty()) {
            for (ConnectionProxy connection: availableConnections) {
                connection.closeConnection();
            }
        }
    }

    private void createConnections(int connectionsCount) throws SQLException {
        try {
            Class.forName(configuration.getDbDriver());

            for (int i = 0; i < connectionsCount; i++) {
                create();
            }
        } catch (ClassNotFoundException e) {
            System.exit(1);
        }

    }

    private void create() throws SQLException {
        ConnectionProxy connectionProxy = new ConnectionProxy(
                DriverManager.getConnection(
                        configuration.getDbUrl(),
                        configuration.getDbUser(),
                        configuration.getDbPassword()
                )
        );

        connectionProxy.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        availableConnections.add(connectionProxy);
    }
}
