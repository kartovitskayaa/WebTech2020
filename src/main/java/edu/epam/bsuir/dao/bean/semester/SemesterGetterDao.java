package edu.epam.bsuir.dao.bean.semester;

import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.dao.exception.DAOException;

import java.util.List;

/**
 * This interface provides with ability to
 * transfer {@code Semester}
 * from data source.
 *
 * @author Maria Kartovitskaya
 */
public interface SemesterGetterDao {

    /**
     * Retrieves data of {@code Semester} from data source.
     *
     * @param courseId ID of {@code Course}.
     * @param studentId ID of {@code Student}.
     * @return {@code Semester}.
     * @throws DAOException when data source throws exception.
     */
    Semester getSemester(int courseId, int studentId) throws DAOException;

    /**
     * Retrieves count of {@code Semester} from data source.
     *
     * @return total number of {@code Semester} in a data source.
     * @throws DAOException when data source throws exception.
     */
    int getTotalSemesterNumber() throws DAOException;

    /**
     * Retrieves specified number of {@code Semester} from data source.
     *
     * @param from from which raw of a data source.
     * @param count how much entities to retrieve.
     * @return {@code Semester}s.
     * @throws DAOException when data source throws exception.
     */
    List<Semester> getSpecifiedNumberOfSemesters(int from, int count) throws DAOException;
}
