package edu.epam.bsuir.dao.bean.student;

import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.dao.exception.DAOException;

import java.util.List;

/**
 * This interface provides with ability to
 * transfer {@code Student}
 * from data source.
 *
 * @author Maria Kartovitskaya
 */
public interface StudentGetterDao {

    /**
     * Retrieves data of {@code Student} from data source
     * if login exist in data source.
     *
     * @param name name of {@code Student}.
     * @return {@code Student}.
     * @throws DAOException when data source throws exception.
     */
    Student getStudentByName(String name) throws DAOException;

    /**
     * Retrieves data of {@code Student} from data source.
     *
     * @param id ID of {@code Student} to be retrieved.
     * @return {@code Student}.
     * @throws DAOException when data source throws exception.
     */
    Student getStudent(int id) throws DAOException;

    /**
     * Retrieves count of {@code Student} from data source.
     *
     * @return total number of {@code Student} in a data source.
     * @throws DAOException when data source throws exception.
     */
    int getTotalStudentNumber() throws DAOException;

    /**
     * Retrieves specified number of {@code Student} from data source.
     *
     * @param from from which raw of a data source.
     * @param count how much entities to retrieve.
     * @return {@code Student}s.
     * @throws DAOException when data source throws exception.
     */
    List<Student> getSpecifiedNumberOfStudents(int from, int count) throws DAOException;
}
