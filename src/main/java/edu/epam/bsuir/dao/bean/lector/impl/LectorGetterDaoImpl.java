package edu.epam.bsuir.dao.bean.lector.impl;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.dao.bean.lector.LectorGetterDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.util.Connections;
import edu.epam.bsuir.db.pool.ConnectionProxy;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectorGetterDaoImpl implements LectorGetterDao {

    private static final Logger LOGGER = LogManager.getLogger(LectorGetterDaoImpl.class);

    private static final String LOGIN_LECTOR =
            "SELECT Lector.id, PersonalData.login, PersonalData.password FROM Elective.Lector " +
            "INNER JOIN PersonalData ON PersonalData.id = Lector.personalDataId " +
            "WHERE Lector.personalDataId in ( " +
            "   SELECT PersonalData.id FROM Elective.PersonalData " +
            "   WHERE PersonalData.login = ?" +
            ")";

    private static final String GET_LECTOR_BY_ID =
            "SELECT Lector.id, PersonalData.login, PersonalData.password FROM Lector " +
            "INNER JOIN PersonalData ON PersonalData.id = Lector.personalDataId " +
            "WHERE Lector.id = ?";

    private static final String GET_LECTOR_COUNT =
            "SELECT COUNT(Lector.id) AS count FROM Elective.Lector";

    private static final String GET_NUMBER_OF_LECTORS =
            "SELECT Lector.id, PersonalData.login, PersonalData.password FROM Lector " +
            "INNER JOIN PersonalData ON PersonalData.id = Lector.personalDataId " +
            "LIMIT ? OFFSET ?";

    @Override
    public Lector getLectorByName(String name) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Lector lector = new Lector();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(LOGIN_LECTOR);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lector.setId(resultSet.getInt("id"));
                lector.setLogin(resultSet.getString("login"));
                lector.setPassword(resultSet.getString("password"));
            } else {
                throw new DAOException("Unable to login lector by it name(" + name + ") and password");
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to login lector by it name(" + name + ") and password");
            throw new DAOException("Unable to login lector by it name(" + name + ") and password");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return lector;
    }

    @Override
    public Lector getLector(int id) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Lector lector = new Lector();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_LECTOR_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lector.setId(resultSet.getInt("id"));
                lector.setLogin(resultSet.getString("login"));
                lector.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get lector by it id(" + id + ")");
            throw new DAOException("Unable to get lector by it id(" + id + ")");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return lector;
    }

    @Override
    public int getTotalLectorNumber() throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_LECTOR_COUNT);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get lector count");
            throw new DAOException("Unable to get lector count");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return count;
    }

    @Override
    public List<Lector> getSpecifiedNumberOfLectors(int from, int count) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lector> lectors = new ArrayList<>();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_NUMBER_OF_LECTORS);
            statement.setInt(1, count);
            statement.setInt(2, from);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Lector lector = new Lector();
                lector.setId(resultSet.getInt("id"));
                lector.setLogin(resultSet.getString("login"));
                lector.setPassword(resultSet.getString("password"));

                lectors.add(lector);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get lectors");
            throw new DAOException("Unable to get lectors)");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return lectors;
    }
}
