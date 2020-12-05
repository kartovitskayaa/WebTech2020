package edu.epam.bsuir.service.bean.course;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.service.exception.ServiceException;

/**
 * This interface provides with ability to
 * transfer {@code Course}
 * to DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface CourseEditorService {

    /**
     * Changes data of {@code Course}
     * in DAO layer.
     *
     * @param course specific {@code Course}.
     * @throws ServiceException when failed to set course.
     */
    void setCourse(Course course) throws ServiceException;

    /**
     * Adds new {@code Course} to DAO layer.
     *
     * @param course {@code Course}.
     * @throws ServiceException when failed to add course.
     */
    void addCourse(Course course) throws ServiceException;
}
