package edu.epam.bsuir.dao.bean.lector.impl;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.dao.bean.lector.LectorEditorDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.util.Connections;
import edu.epam.bsuir.dao.util.EditorUtil;
import edu.epam.bsuir.db.pool.ConnectionProxy;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LectorEditorDaoImpl implements LectorEditorDao {

    private static final Logger LOGGER = LogManager.getLogger(LectorEditorDaoImpl.class);

    private static final String ADD_LECTOR =
            "INSERT INTO Elective.Lector (personalDataId) VALUES (?)";

    @Override
    public boolean setLector(Lector lector) throws DAOException {
        return EditorUtil.setPersonalData(lector.getLogin(), lector.getPassword(),
                EditorUtil.getPersonalDataId(lector.getId(), EditorUtil.UserType.LECTOR));
    }

    @Override
    public int addLector(Lector lector) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        int id;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_LECTOR, Statement.RETURN_GENERATED_KEYS);
            int personalDataId = EditorUtil.addPersonalData(lector.getLogin(), lector.getPassword());
            statement.setInt(1, personalDataId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating lector failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new DAOException("Creating lector failed, no ID obtained.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                LOGGER.error("Unable to rollback adding lector");
            }
            LOGGER.error("Unable to create adding lector");
            throw new DAOException("Unable to create adding lector");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return id;
    }
}
