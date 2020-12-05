package edu.epam.bsuir.service.bean.semester;

import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.service.exception.ServiceException;

/**
 * This interface provides with ability to
 * transfer {@code Semester}
 * to DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface SemesterEditorService {

    /**
     * Changes data of {@code Semester}
     * in DAO layer.
     *
     * @param semester {@code Semester}.
     * @throws ServiceException when failed to set semester.
     */
    void setSemester(Semester semester) throws ServiceException;

    /**
     * Adds new implementors of {@code Semester}
     * to DAO layer.
     *
     * @param semester {@code Semester}.
     * @throws ServiceException when failed to add semester.
     */
    void addSemester(Semester semester) throws ServiceException;
}
