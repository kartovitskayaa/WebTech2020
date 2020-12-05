package edu.epam.bsuir.dao.bean.course;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.dao.exception.DAOException;

/**
 * This interface provides with ability to
 * transfer {@code Course}
 * to data source.
 *
 * @author Maria Kartovitskaya
 */
public interface CourseEditorDao {

    /**
     * Changes data of {@code Course}
     * in data source.
     *
     * @param course specific {@code Course}.
     * @return whether transaction was successful.
     * @throws DAOException when data source throws exception.
     */
    boolean setCourse(Course course) throws DAOException;

    /**
     * Adds new {@code Course} to data source.
     *
     * @param course {@code Course}.
     * @return id of a newly created Course.
     * @throws DAOException when data source throws exception.
     */
    int addCourse(Course course) throws DAOException;
}
