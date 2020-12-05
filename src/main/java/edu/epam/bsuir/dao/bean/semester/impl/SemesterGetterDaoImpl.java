package edu.epam.bsuir.dao.bean.semester.impl;

import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.bean.semester.SemesterGetterDao;
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

public class SemesterGetterDaoImpl implements SemesterGetterDao {

    private static final Logger LOGGER = LogManager.getLogger(SemesterGetterDaoImpl.class);

    private static final String GET_SEMESTER =
            "SELECT mark, comment FROM Elective.Semester " +
            "WHERE courseId = ? AND studentId = ?";

    private static final String GET_SEMESTER_COUNT =
            "SELECT COUNT(studentId AND courseId) AS count FROM Elective.Semester";

    private static final String GET_NUMBER_OF_SEMESTERS =
            "SELECT mark, comment FROM Elective.Semester " +
            "LIMIT ? OFFSET ?";

    @Override
    public Semester getSemester(int courseId, int studentId) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Semester semester = new Semester();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_SEMESTER);
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                semester.setMark(resultSet.getInt("mark"));
                semester.setMessage(resultSet.getString("comment"));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get semester by it course id(" + courseId + ") " +
                    "and student id(" + studentId +")");
            throw new DAOException("Unable to get semester by it course id(" + courseId + ") " +
                    "and student id(" + studentId +")");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return semester;
    }

    @Override
    public int getTotalSemesterNumber() throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_SEMESTER_COUNT);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get semester count");
            throw new DAOException("Unable to get semester count");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return count;
    }

    @Override
    public List<Semester> getSpecifiedNumberOfSemesters(int from, int count) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Semester> semesters = new ArrayList<>();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_NUMBER_OF_SEMESTERS);
            statement.setInt(1, count);
            statement.setInt(2, from);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Semester semester = new Semester();
                semester.setMark(resultSet.getInt("mark"));
                semester.setMessage(resultSet.getString("comment"));

                semesters.add(semester);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get semesters");
            throw new DAOException("Unable to get semesters)");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return semesters;
    }
}
