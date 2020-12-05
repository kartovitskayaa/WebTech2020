package edu.epam.bsuir.dao.bean.semester.impl;

import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.bean.semester.SemesterEditorDao;
import edu.epam.bsuir.dao.util.Connections;
import edu.epam.bsuir.db.pool.ConnectionProxy;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SemesterEditorDaoImpl implements SemesterEditorDao {

    private static final Logger LOGGER = LogManager.getLogger(SemesterEditorDaoImpl.class);

    private static final String SET_SEMESTER =
            "UPDATE Elective.Semester SET mark = ?, comment = ? " +
            "WHERE (courseId = ?) and (studentId = ?)";

    private static final String ADD_SEMESTER =
            "INSERT INTO Elective.Semester (courseId, studentId, mark, comment) " +
            "VALUES (?, ?, ?, ?)";

    @Override
    public boolean setSemester(Semester bean) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SET_SEMESTER);
            statement.setInt(1, bean.getMark());
            statement.setString(2, bean.getMessage());
            statement.setInt(3, bean.getCourse().getId());
            statement.setInt(4, bean.getStudent().getId());

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
                LOGGER.error("Unable to rollback updating semester");
            }
            LOGGER.error("Unable to update semester");
            throw new DAOException("Unable to update semester");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return true;
    }

    @Override
    public boolean addSemester(Semester bean) throws DAOException {
        ConnectionProxy connection = null;
        PreparedStatement statement = null;

        try {
            connection = ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_SEMESTER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bean.getCourse().getId());
            statement.setInt(2, bean.getStudent().getId());
            statement.setInt(3, bean.getMark());
            statement.setString(4, bean.getMessage());

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
                LOGGER.error("Unable to rollback adding semester");
            }
            LOGGER.error("Unable to create semester");
            throw new DAOException("Unable to create semester");
        } finally {
            Connections.closeConnections(connection, statement, null);
        }

        return true;
    }
}
