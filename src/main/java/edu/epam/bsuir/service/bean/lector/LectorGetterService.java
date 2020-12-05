package edu.epam.bsuir.service.bean.lector;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.service.exception.ServiceException;

import java.util.List;

/**
 * This interface provides with ability to
 * transfer {@code Lector}
 * from DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface LectorGetterService {

    /**
     * Retrieves data of {@code Lector} from DAO layer
     * if login and password exist in data source.
     *
     * @param name name of {@code Lector}.
     * @param password password of {@code Lector}.
     * @return {@code Lector}.
     * @throws ServiceException when failed to login.
     */
    Lector login(String name, String password) throws ServiceException;

    /**
     * Retrieves data of {@code Lector} from DAO layer.
     *
     * @param id ID of {@code Lector} to be retrieved.
     * @return {@code Course}.
     * @throws ServiceException when failed to get lector.
     */
    Lector getLector(int id) throws ServiceException;

    /**
     * Retrieves count of {@code Lector} from DAO layer.
     *
     * @return total number of {@code Lector} in a data source.
     * @throws ServiceException when failed to get total number of lector.
     */
    int getTotalLectorNumber() throws ServiceException;

    /**
     * Retrieves specified number of {@code Lector} from DAO layer.
     *
     * @param from from which raw of a data source.
     * @param count how much entities to retrieve.
     * @return {@code Lector}s.
     * @throws ServiceException when failed to get lectors.
     */
    List<Lector> getSpecifiedNumberOfLectors(int from, int count) throws ServiceException;
}
