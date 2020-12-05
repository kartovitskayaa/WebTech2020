package edu.epam.bsuir.dao.bean.course.impl;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.dao.bean.course.CourseEditorDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.util.Connections;
import edu.epam.bsuir.db.pool.ConnectionProxy;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseEditorDaoImpl implements CourseEditorDao {

    private static final Logger LOGGER = LogManager.getLogger(CourseEditorDaoImpl.class);

    private static final String SET_COURSE =
            "UPDATE Elective.Course SET name = ?, finished = ?, startDate = ?, endDate = ?, lectorId = ? " +
            "WHERE (id = ?) ";

    private static final String ADD_COURSE =
            "INSERT INTO Elective.Course (name, finished, startDate, endDate, lectorId) " +
            "VALUES (?, ?, ?, ?, ?)";

    @Override
    public boolean setCourse(Course course) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SET_COURSE);
            statement.setString(1, course.getName());
            statement.setBoolean(2, course.isFinished());
            statement.setTimestamp(3, course.getStartDate());
            statement.setTimestamp(4, course.getEndDate());
            statement.setInt(5, course.getLector().getId());
            statement.setInt(6, course.getId());

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
                LOGGER.error("Unable to rollback updating course");
            }
            LOGGER.error("Unable to update course");
            throw new DAOException("Unable to update course");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return true;
    }

    @Override
    public int addCourse(Course course) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        int id;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_COURSE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, course.getName());
            statement.setBoolean(2, course.isFinished());
            statement.setTimestamp(3, course.getStartDate());
            statement.setTimestamp(4, course.getEndDate());
            statement.setInt(5, course.getLector().getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating course failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new DAOException("Creating course failed, no ID obtained.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                LOGGER.error("Unable to rollback adding course");
            }
            LOGGER.error("Unable to create course");
            throw new DAOException("Unable to create course");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return id;
    }
}
