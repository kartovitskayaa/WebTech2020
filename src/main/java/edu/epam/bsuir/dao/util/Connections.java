package edu.epam.bsuir.dao.util;

import edu.epam.bsuir.db.pool.ConnectionProxy;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This {@code Connections} class provides with ability to
 * close {@code Connection}, {@code PreparedStatement}
 * and {@code ResultSet}.
 *
 * @author Maria Kartovitskaya
 */
public class Connections {

    private static final Logger LOGGER = LogManager.getLogger(Connections.class);

    private Connections(){
    }

    public static void closeConnections(
            ConnectionProxy connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        close(connection, preparedStatement, resultSet);
    }

    private static void close(ConnectionProxy connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        releaseConnectionToPool(connection);
        closePreparedStatement(preparedStatement);
        closeResultSet(resultSet);
    }

    private static void releaseConnectionToPool(ConnectionProxy connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error("Error releasing Connection");
            }
        }
    }

    private static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing PreparedStatement");
            }
        }

    }

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing ResultSet");
            }
        }
    }
}