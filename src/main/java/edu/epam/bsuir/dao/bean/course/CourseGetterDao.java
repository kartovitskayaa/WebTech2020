package edu.epam.bsuir.dao.bean.course;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.dao.exception.DAOException;

import java.util.List;

/**
 * This interface provides with ability to
 * transfer {@code Course}
 * from data source.
 *
 * @author Maria Kartovitskaya
 */
public interface CourseGetterDao {

    /**
     * Retrieves data of {@code Course} from data source.
     *
     * @param id ID of {@code Course} to be retrieved.
     * @return {@code Course}.
     * @throws DAOException when data source throws exception.
     */
    Course getCourse(int id) throws DAOException;

    /**
     * Retrieves count of {@code Course} from data source.
     *
     * @return total number of {@code Course} in a data source.
     * @throws DAOException when data source throws exception.
     */
    int getTotalCourseNumber() throws DAOException;

    /**
     * Retrieves specified number of {@code Course} from data source.
     *
     * @param from from which raw of a data source.
     * @param count how much entities to retrieve.
     * @return {@code Course}s.
     * @throws DAOException when data source throws exception.
     */
    List<Course> getSpecifiedNumberOfCourses(int from, int count) throws DAOException;
}
