package edu.epam.bsuir.service.bean.lector.impl;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.dao.bean.lector.LectorGetterDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.lector.LectorGetterService;
import edu.epam.bsuir.service.exception.ServiceException;
import edu.epam.bsuir.service.util.AuthenticationUtil;

import java.util.List;

public class LectorGetterServiceImpl implements LectorGetterService {

    private final LectorGetterDao lectorGetterDao;

    public LectorGetterServiceImpl(AbstractFactoryDao factoryDao) {
        lectorGetterDao = factoryDao.getLectorGetterDao();
    }

    @Override
    public Lector login(String name, String password) throws ServiceException {
        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            throw new ServiceException("Wrong input");
        }
        try {
            Lector lector = lectorGetterDao.getLectorByName(name);

            if (lector == null || !AuthenticationUtil.checkPassword(password, lector.getPassword())) {
                throw new ServiceException("Failed to login");
            }

            return lector;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Lector getLector(int id) throws ServiceException {
        if (id <= 0) {
            throw new ServiceException("Failed to validate: id is negative");
        }
        try {
            Lector lector = lectorGetterDao.getLector(id);
            if (lector == null) {
                throw new ServiceException("Failed to get lector");
            }

            return lector;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getTotalLectorNumber() throws ServiceException {
        try {
            return lectorGetterDao.getTotalLectorNumber();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Lector> getSpecifiedNumberOfLectors(int from, int count) throws ServiceException {
        if (from < 0 || count <= 0) {
            throw new ServiceException("Wrong input");
        }
        try {
            List<Lector> lectors = lectorGetterDao.getSpecifiedNumberOfLectors(from, count);
            if (lectors.isEmpty()) {
                throw new ServiceException("Failed to get specified lectors");
            }
            return lectors;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
