package edu.epam.bsuir.dao.bean.semester;

import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.dao.exception.DAOException;

/**
 * This interface provides with ability to
 * transfer {@code Semester}
 * to data source.
 *
 * @author Maria Kartovitskaya
 */
public interface SemesterEditorDao {

    /**
     * Changes data of {@code Semester}
     * in data source.
     *
     * @param semester {@code Semester}.
     * @return whether transaction was successful.
     * @throws DAOException when data source throws exception.
     */
    boolean setSemester(Semester semester) throws DAOException;

    /**
     * Adds new implementors of {@code Semester}
     * to data source.
     *
     * @param semester {@code Semester}.
     * @return id of a newly created Semester.
     * @throws DAOException when data source throws exception.
     */
    boolean addSemester(Semester semester) throws DAOException;
}
