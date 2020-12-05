package edu.epam.bsuir.dao.bean.student.impl;

import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.dao.bean.student.StudentGetterDao;
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

public class StudentGetterDaoImpl implements StudentGetterDao {

    private static final Logger LOGGER = LogManager.getLogger(StudentGetterDaoImpl.class);

    private static final String LOGIN_STUDENT =
            "SELECT Student.id, PersonalData.login, PersonalData.password, Student.avgMark FROM Student " +
            "INNER JOIN PersonalData ON PersonalData.id = Student.personalDataId " +
            "WHERE Student.personalDataId in ( " +
            "   SELECT PersonalData.id FROM Elective.PersonalData " +
            "   WHERE PersonalData.login = ?" +
            ")";

    private static final String GET_STUDENT_BY_ID =
            "SELECT Student.id, PersonalData.login, PersonalData.password, Student.avgMark FROM Student " +
            "INNER JOIN PersonalData ON PersonalData.id = Student.personalDataId " +
            "WHERE Student.id = ?";

    private static final String GET_STUDENT_COUNT =
            "SELECT COUNT(Student.id) AS count FROM Elective.Student";

    private static final String GET_NUMBER_OF_STUDENTS =
            "SELECT Student.id, PersonalData.login, PersonalData.password, Student.avgMark FROM Student " +
            "INNER JOIN PersonalData ON PersonalData.id = Student.personalDataId " +
            "LIMIT ? OFFSET ?";


    @Override
    public Student getStudentByName(String name) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = new Student();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(LOGIN_STUDENT);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setLogin(resultSet.getString("login"));
                student.setPassword(resultSet.getString("password"));
                student.setAvgMark(resultSet.getDouble("avgMark"));
            } else {
                throw new DAOException("Unable to login student by it name(" + name + ") and password");
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to login student by it name(" + name + ") and password");
            throw new DAOException("Unable to login student by it name(" + name + ") and password");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return student;
    }

    @Override
    public Student getStudent(int id) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = new Student();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_STUDENT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setLogin(resultSet.getString("login"));
                student.setPassword(resultSet.getString("password"));
                student.setAvgMark(resultSet.getDouble("avgMark"));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get student by it id(" + id + ")");
            throw new DAOException("Unable to get student by it id(" + id + ")");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return student;
    }

    @Override
    public int getTotalStudentNumber() throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_STUDENT_COUNT);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get student count");
            throw new DAOException("Unable to get student count");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return count;
    }

    @Override
    public List<Student> getSpecifiedNumberOfStudents(int from, int count) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<>();

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            statement = connection.prepareStatement(GET_NUMBER_OF_STUDENTS);
            statement.setInt(1, count);
            statement.setInt(2, from);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setLogin(resultSet.getString("login"));
                student.setPassword(resultSet.getString("password"));
                student.setAvgMark(resultSet.getDouble("avgMark"));

                students.add(student);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get students");
            throw new DAOException("Unable to get students)");
        } finally {
            Connections.closeConnections(connection, statement, resultSet);
        }

        return students;
    }
}
