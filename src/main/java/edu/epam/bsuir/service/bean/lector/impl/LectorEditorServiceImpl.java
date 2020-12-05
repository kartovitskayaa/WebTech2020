package edu.epam.bsuir.service.bean.lector.impl;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.dao.bean.lector.LectorEditorDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.lector.LectorEditorService;
import edu.epam.bsuir.service.exception.ServiceException;
import edu.epam.bsuir.service.util.AuthenticationUtil;
import edu.epam.bsuir.service.util.BeanValidator;

public class LectorEditorServiceImpl implements LectorEditorService {

    private final LectorEditorDao lectorEditorDao;

    public LectorEditorServiceImpl(AbstractFactoryDao factoryDao) {
        lectorEditorDao = factoryDao.getLectorEditorDao();
    }

    @Override
    public void setLector(Lector lector) throws ServiceException {
        BeanValidator.validateLector(lector);
        try {
            boolean result = lectorEditorDao.setLector(lector);
            if (!result) {
                throw new ServiceException("Failed to set lector");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void signUpLector(Lector lector) throws ServiceException {
        BeanValidator.validateLector(lector);
        try {
            lector.setPassword(AuthenticationUtil.getHash(lector.getPassword()));
            int id = lectorEditorDao.addLector(lector);
            if (id <= 0) {
                throw new ServiceException("Failed to add lector: id is negative");
            }

            lector.setId(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
