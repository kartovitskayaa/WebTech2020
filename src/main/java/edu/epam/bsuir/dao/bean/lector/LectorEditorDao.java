package edu.epam.bsuir.dao.bean.lector;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.dao.exception.DAOException;

/**
 * This interface provides with ability to
 * transfer {@code Lector}
 * to data source.
 *
 * @author Maria Kartovitskaya
 */
public interface LectorEditorDao {

    /**
     * Changes data of {@code Lector}
     * in data source.
     *
     * @param lector specific {@code Lector}.
     * @return whether transaction was successful.
     * @throws DAOException when data source throws exception.
     */
    boolean setLector(Lector lector) throws DAOException;

    /**
     * Adds new {@code Lector} to data source.
     *
     * @param lector {@code Lector}.
     * @return id of a newly created Lector.
     * @throws DAOException when data source throws exception.
     */
    int addLector(Lector lector) throws DAOException;
}
