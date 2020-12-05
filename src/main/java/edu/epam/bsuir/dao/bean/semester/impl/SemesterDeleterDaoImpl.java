package edu.epam.bsuir.dao.bean.semester.impl;

import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.bean.semester.SemesterDeleterDao;
import edu.epam.bsuir.dao.util.Connections;
import edu.epam.bsuir.db.pool.ConnectionProxy;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SemesterDeleterDaoImpl implements SemesterDeleterDao {

    private static final Logger LOGGER = LogManager.getLogger(SemesterDeleterDaoImpl.class);

    private static final String DELETE_SEMESTER =
            "DELETE FROM Elective.Semester WHERE (courseId = ?) and (studentId = ?)";

    @Override
    public boolean deleteSemester(int courseId, int studentId) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(DELETE_SEMESTER);
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);

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
                LOGGER.error("Unable to rollback deleting semester");
            }
            LOGGER.error("Unable to delete semester");
            throw new DAOException("Unable to delete semester");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return true;
    }
}
