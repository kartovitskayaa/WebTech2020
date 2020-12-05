package edu.epam.bsuir.service.bean.course;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.service.exception.ServiceException;

import java.util.List;

/**
 * This interface provides with ability to
 * transfer {@code Course}
 * from DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface CourseGetterService {

    /**
     * Retrieves data of {@code Course} from DAO layer.
     *
     * @param id ID of {@code Course} to be retrieved.
     * @return {@code Course}.
     * @throws ServiceException when failed to get course.
     */
    Course getCourse(int id) throws ServiceException;

    /**
     * Retrieves count of {@code Course} from DAO layer.
     *
     * @return total number of {@code Course} in a data source.
     * @throws ServiceException when failed to get total number of courses.
     */
    int getTotalCourseNumber() throws ServiceException;

    /**
     * Retrieves specified number of {@code Course} from DAO layer.
     *
     * @param from from which raw of a data source.
     * @param count how much entities to retrieve.
     * @return {@code Course}s.
     * @throws ServiceException when failed to get courses.
     */
    List<Course> getSpecifiedNumberOfCourses(int from, int count) throws ServiceException;
}
