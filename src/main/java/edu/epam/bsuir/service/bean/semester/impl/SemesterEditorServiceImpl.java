package edu.epam.bsuir.service.bean.semester.impl;

import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.dao.bean.semester.SemesterEditorDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.semester.SemesterEditorService;
import edu.epam.bsuir.service.exception.ServiceException;
import edu.epam.bsuir.service.util.BeanValidator;

public class SemesterEditorServiceImpl implements SemesterEditorService {

    private final SemesterEditorDao semesterEditorDao;

    public SemesterEditorServiceImpl(AbstractFactoryDao factoryDao) {
        semesterEditorDao = factoryDao.getSemesterEditorDao();
    }

    @Override
    public void setSemester(Semester semester) throws ServiceException {
        BeanValidator.validateSemester(semester);
        try {
            boolean result = semesterEditorDao.setSemester(semester);
            if (!result) {
                throw new ServiceException("Failed to set semester");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void addSemester(Semester semester) throws ServiceException {
        BeanValidator.validateSemester(semester);
        try {
            boolean result = semesterEditorDao.addSemester(semester);
            if (!result) {
                throw new ServiceException("Failed to add semester");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
