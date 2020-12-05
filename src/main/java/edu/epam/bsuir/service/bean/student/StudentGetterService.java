package edu.epam.bsuir.service.bean.student;

import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.service.exception.ServiceException;

import java.util.List;

/**
 * This interface provides with ability to
 * transfer {@code Student}
 * from DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface StudentGetterService {

    /**
     * Retrieves data of {@code Student} from DAO layer
     * if login and password exist in data source.
     *
     * @param name name of {@code Student}.
     * @param password password of {@code Student}.
     * @return {@code Student}.
     * @throws ServiceException when failed to login.
     */
    Student login(String name, String password) throws ServiceException;

    /**
     * Retrieves data of {@code Student} from DAO layer.
     *
     * @param id ID of {@code Student} to be retrieved.
     * @return {@code Student}.
     * @throws ServiceException when failed to get student.
     */
    Student getStudent(int id) throws ServiceException;

    /**
     * Retrieves count of {@code Student} from DAO layer.
     *
     * @return total number of {@code Student} in a data source.
     * @throws ServiceException when failed to get total number of students.
     */
    int getTotalStudentNumber() throws ServiceException;

    /**
     * Retrieves specified number of {@code Student} from data source.
     *
     * @param from from which raw of a data source.
     * @param count how much entities to retrieve.
     * @return {@code Student}s.
     * @throws ServiceException when failed to get students.
     */
    List<Student> getSpecifiedNumberOfStudents(int from, int count) throws ServiceException;
}
