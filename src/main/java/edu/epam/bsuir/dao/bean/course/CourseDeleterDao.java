package edu.epam.bsuir.dao.bean.course;

import edu.epam.bsuir.dao.exception.DAOException;

/**
 * This interface provides with ability to
 * delete {@code Course}
 * in data source.
 *
 * @author Maria Kartovitskaya
 */
public interface CourseDeleterDao {

    /**
     * Deletes {@code Course} from data source.
     *
     * @param id ID of {@code Course}.
     * @return whether transaction was successful.
     * @throws DAOException when data source throws exception.
     */
    boolean deleteCourse(int id) throws DAOException;
}
