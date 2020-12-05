package edu.epam.bsuir.dao.bean.student;

import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.dao.exception.DAOException;

/**
 * This interface provides with ability to
 * transfer {@code Student}
 * to data source.
 *
 * @author Maria Kartovitskaya
 */
public interface StudentEditorDao {

    /**
     * Changes data of {@code Student}
     * in data source.
     *
     * @param student specific {@code Student}.
     * @return whether transaction was successful.
     * @throws DAOException when data source throws exception.
     */
    boolean setStudent(Student student) throws DAOException;

    /**
     * Adds new {@code Student} to data source.
     *
     * @param student {@code Student}.
     * @return id of a newly created Student.
     * @throws DAOException when data source throws exception.
     */
    int addStudent(Student student) throws DAOException;
}
