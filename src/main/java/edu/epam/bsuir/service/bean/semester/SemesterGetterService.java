package edu.epam.bsuir.service.bean.semester;

import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.service.exception.ServiceException;

import java.util.List;

/**
 * This interface provides with ability to
 * transfer {@code Semester}
 * from DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface SemesterGetterService {

    /**
     * Retrieves data of {@code Semester} from DAO layer.
     *
     * @param courseId ID of {@code Course}.
     * @param studentId ID of {@code Student}.
     * @return {@code Semester}.
     * @throws SecurityException when failed to get semester.
     */
    Semester getSemester(int courseId, int studentId) throws ServiceException;

    /**
     * Retrieves count of {@code Semester} from DAO layer.
     *
     * @return total number of {@code Semester} in a data source.
     * @throws ServiceException when failed to get total semester number.
     */
    int getTotalSemesterNumber() throws ServiceException;

    /**
     * Retrieves specified number of {@code Semester} from DAO layer.
     *
     * @param from from which raw of a data source.
     * @param count how much entities to retrieve.
     * @return {@code Semester}s.
     * @throws ServiceException when failed to get semesters.
     */
    List<Semester> getSpecifiedNumberOfSemesters(int from, int count) throws ServiceException;
}
