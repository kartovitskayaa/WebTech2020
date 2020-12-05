package edu.epam.bsuir.dao.bean.course.impl;

import edu.epam.bsuir.dao.bean.course.CourseDeleterDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.util.Connections;
import edu.epam.bsuir.db.pool.ConnectionProxy;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseDeleterDaoImpl implements CourseDeleterDao {

    private static final Logger LOGGER = LogManager.getLogger(CourseDeleterDaoImpl.class);

    private static final String DELETE_COURSE =
            "DELETE FROM Elective.Course WHERE (id = ?)";

    @Override
    public boolean deleteCourse(int id) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(DELETE_COURSE);
            statement.setInt(1, id);

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
                LOGGER.error("Unable to rollback deleting course");
            }
            LOGGER.error("Unable to delete course");
            throw new DAOException("Unable to delete course");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return true;
    }
}
