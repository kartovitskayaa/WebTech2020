package edu.epam.bsuir.service.bean.course;

import edu.epam.bsuir.service.exception.ServiceException;

/**
 * This interface provides with ability to
 * delete {@code Course}
 * in DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface CourseDeleterService {

    /**
     * Deletes {@code Course} from DAO layer.
     *
     * @param id ID of {@code Course}.
     * @throws ServiceException when failed to delete course.
     */
    void deleteCourse(int id) throws ServiceException;
}
