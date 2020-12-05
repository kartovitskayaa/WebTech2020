package edu.epam.bsuir.service.bean.student;

import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.service.exception.ServiceException;

/**
 * This interface provides with ability to
 * transfer {@code Student}
 * to DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface StudentEditorService {

    /**
     * Changes data of {@code Student}
     * in DAO layer.
     *
     * @param student {@code Student}.
     * @throws ServiceException when failed to set student.
     */
    void setStudent(Student student) throws ServiceException;

    /**
     * Adds new {@code Student} to data source.
     *
     * @param student {@code Student}.
     * @throws ServiceException when failed to add student.
     */
    void signUpStudent(Student student) throws ServiceException;
}
