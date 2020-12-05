package edu.epam.bsuir.dao.bean.course.impl;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.dao.bean.course.CourseGetterDao;
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

public class CourseGetterDaoImpl implements CourseGetterDao {

    private static final Logger LOGGER = LogManager.getLogger(CourseGetterDaoImpl.class);

    private static final String GET_COURSE_BY_ID =
            "SELECT * FROM Elective.Course " +
            "WHERE id = ?";

    private static final String GET_COURSE_COUNT =
            "SELECT COUNT(Course.id) AS count FROM Elective.Course";

    private static final String GET_NUMBER_OF_LECTORS =
            "SELECT * FROM Course " +
            "LIMIT ? OFFSET ?";

    @Override
    public Course getCourse(int id) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Course course = new Course();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_COURSE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setFinished(resultSet.getBoolean("finished"));
                course.setStartDate(resultSet.getTimestamp("startDate"));
                course.setEndDate(resultSet.getTimestamp("endDate"));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get course by it id(" + id + ")");
            throw new DAOException("Unable to get course by it id(" + id + ")");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return course;
    }

    @Override
    public int getTotalCourseNumber() throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_COURSE_COUNT);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get course count");
            throw new DAOException("Unable to get course count");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return count;
    }

    @Override
    public List<Course> getSpecifiedNumberOfCourses(int from, int count) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Course> courses = new ArrayList<>();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_NUMBER_OF_LECTORS);
            statement.setInt(1, count);
            statement.setInt(2, from);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setFinished(resultSet.getBoolean("finished"));
                course.setStartDate(resultSet.getTimestamp("startDate"));
                course.setEndDate(resultSet.getTimestamp("endDate"));

                courses.add(course);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get lectors");
            throw new DAOException("Unable to get lectors)");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return courses;
    }
}
