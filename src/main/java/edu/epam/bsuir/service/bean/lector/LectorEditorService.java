package edu.epam.bsuir.service.bean.lector;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.service.exception.ServiceException;

/**
 * This interface provides with ability to
 * transfer {@code Lector}
 * to DAO layer.
 *
 * @author Maria Kartovitskaya
 */
public interface LectorEditorService {

    /**
     * Changes data of {@code Lector}
     * in DAO layer.
     *
     * @param lector specific {@code Lector}.
     * @throws ServiceException when failed to set lector.
     */
    void setLector(Lector lector) throws ServiceException;

    /**
     * Adds new {@code Lector} to data source.
     *
     * @param lector {@code Lector}.
     * @throws ServiceException when failed to add lector.
     */
    void signUpLector(Lector lector) throws ServiceException;
}
