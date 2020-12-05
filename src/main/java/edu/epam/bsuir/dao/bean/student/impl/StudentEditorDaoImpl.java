package edu.epam.bsuir.dao.bean.student.impl;

import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.dao.bean.student.StudentEditorDao;
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

public class StudentEditorDaoImpl implements StudentEditorDao {

    private static final Logger LOGGER = LogManager.getLogger(StudentEditorDaoImpl.class);

    private static final String ADD_STUDENT =
            "INSERT INTO Elective.Student (personalDataId, avgMark) VALUES (?, ?)";

    private static final String SET_STUDENT =
            "UPDATE Elective.Student SET avgMark = ? WHERE (id = ?)";

    @Override
    public boolean setStudent(Student student) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SET_STUDENT);
            statement.setDouble(1, student.getAvgMark());
            statement.setInt(2, student.getId());

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
                LOGGER.error("Unable to rollback updating student");
            }
            LOGGER.error("Unable to update student");
            throw new DAOException("Unable to update student");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return EditorUtil.setPersonalData(student.getLogin(), student.getPassword(),
                EditorUtil.getPersonalDataId(student.getId(), EditorUtil.UserType.STUDENT));
    }

    @Override
    public int addStudent(Student student) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;
        int id;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_STUDENT, Statement.RETURN_GENERATED_KEYS);
            int personalDataId = EditorUtil.addPersonalData(student.getLogin(), student.getPassword());
            statement.setInt(1, personalDataId);
            statement.setDouble(2, student.getAvgMark());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating student failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new DAOException("Creating student failed, no ID obtained.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                LOGGER.error("Unable to rollback adding student");
            }
            LOGGER.error("Unable to create student");
            throw new DAOException("Unable to create student");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return id;
    }
}
