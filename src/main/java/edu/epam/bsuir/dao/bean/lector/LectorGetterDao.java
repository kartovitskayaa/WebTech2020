package edu.epam.bsuir.dao.bean.lector;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.dao.exception.DAOException;

import java.util.List;

/**
 * This interface provides with ability to
 * transfer {@code Lector}
 * to data source.
 *
 * @author Maria Kartovitskaya
 */
public interface LectorGetterDao {

    /**
     * Retrieves data of {@code Lector} from data source
     * if name exist in data source.
     *
     * @param name name of {@code Lector}.
     * @return {@code Lector}.
     * @throws DAOException when data source throws exception.
     */
    Lector getLectorByName(String name) throws DAOException;

    /**
     * Retrieves data of {@code Lector} from data source.
     *
     * @param id ID of {@code Lector} to be retrieved.
     * @return {@code Lector}.
     * @throws DAOException when data source throws exception.
     */
    Lector getLector(int id) throws DAOException;

    /**
     * Retrieves count of {@code Lector} from data source.
     *
     * @return total number of {@code Lector} in a data source.
     * @throws DAOException when data source throws exception.
     */
    int getTotalLectorNumber() throws DAOException;

    /**
     * Retrieves specified number of {@code Lector} from data source.
     *
     * @param from from which raw of a data source.
     * @param count how much entities to retrieve.
     * @return {@code Lector}s.
     * @throws DAOException when data source throws exception.
     */
    List<Lector> getSpecifiedNumberOfLectors(int from, int count) throws DAOException;
}
