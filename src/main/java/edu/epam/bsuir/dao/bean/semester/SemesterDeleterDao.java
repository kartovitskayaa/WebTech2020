package edu.epam.bsuir.dao.bean.semester;

import edu.epam.bsuir.dao.exception.DAOException;

/**
 * This interface provides with ability to
 * delete {@code Semester}
 * in data source.
 *
 * @author Maria Kartovitskaya
 */
public interface SemesterDeleterDao {

    /**
     * Deletes {@code Semester} from data source.
     *
     * @param courseId ID of {@code Course}.
     * @param studentId ID of {@code Student}.
     * @return whether transaction was successful.
     * @throws DAOException when data source throws exception.
     */
    boolean deleteSemester(int courseId, int studentId) throws DAOException;
}
