package edu.epam.bsuir.service.bean.semester;

import edu.epam.bsuir.service.exception.ServiceException;

/**
 * This interface provides with ability to
 * delete {@code Semester}
 * in DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface SemesterDeleterService {

    /**
     * Deletes {@code Semester} from DAO layer.
     *
     * @param courseId ID of {@code Course}.
     * @param studentId ID of {@code Student}.
     * @throws ServiceException when failed to delete semester.
     */
    void deleteSemester(int courseId, int studentId) throws ServiceException;
}
