package edu.epam.bsuir.dao.util;

import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.db.pool.ConnectionProxy;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditorUtil {

    public enum UserType {
        STUDENT, LECTOR
    }

    private static final Logger LOGGER = LogManager.getLogger(EditorUtil.class);

    private static final String ADD_PERSONAL_DATA =
            "INSERT INTO Elective.PersonalData (login, password) VALUES (?, ?)";

    private static final String SET_PERSONAL_DATA =
            "UPDATE Elective.PersonalData SET login = ?, password = ? WHERE (id = ?)";

    private static final String GET_STUDENT_DATA_ID =
            "SELECT personalDataId FROM Student " +
            "WHERE id = ?";

    private static final String GET_LECTOR_DATA_ID =
            "SELECT personalDataId FROM Lector " +
            "WHERE id = ?";

    public static int addPersonalData(String login, String password) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        int id;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_PERSONAL_DATA, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, login);
            statement.setString(2, password);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating personal data failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating personal data failed, no ID obtained.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                LOGGER.error("Unable to rollback adding personal data");
            }
            LOGGER.error("Unable to create student data");
            throw new DAOException("Unable to create student data");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return id;
    }

    public static boolean setPersonalData(String login, String password, int personalDataId) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SET_PERSONAL_DATA);
            statement.setString(1,login);
            statement.setString(2, password);
            statement.setInt(3, personalDataId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                LOGGER.error("Unable to rollback updating personal data");
            }
            LOGGER.error("Updating personal data failed");
            throw new DAOException("Updating personal data failed");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return true;
    }

    public static int getPersonalDataId(int beanId, UserType type) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int personalDataId;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();

            if (type == UserType.LECTOR) {
                statement = connection.prepareStatement(GET_LECTOR_DATA_ID);
            } else {
                statement = connection.prepareStatement(GET_STUDENT_DATA_ID);
            }

            statement.setInt(1, beanId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                personalDataId = resultSet.getInt("personalDataId");
            } else {
                throw new DAOException("Unable to get personal data id by bean id: (" + beanId + ")");
            }
        } catch (SQLException | DAOException e) {
            LOGGER.error("Unable to get personal data id by bean id: (" + beanId + ")");
            throw new DAOException("Unable to get personal data id by bean id: (" + beanId + ")");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return personalDataId;
    }
}
