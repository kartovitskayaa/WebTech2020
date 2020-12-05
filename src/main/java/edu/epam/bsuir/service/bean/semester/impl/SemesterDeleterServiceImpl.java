package edu.epam.bsuir.service.bean.semester.impl;

import edu.epam.bsuir.dao.bean.semester.SemesterDeleterDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.semester.SemesterDeleterService;
import edu.epam.bsuir.service.exception.ServiceException;

public class SemesterDeleterServiceImpl implements SemesterDeleterService {

    private final SemesterDeleterDao semesterDeleterDao;

    public SemesterDeleterServiceImpl(AbstractFactoryDao factoryDao) {
        semesterDeleterDao = factoryDao.getSemesterDeleterDao();
    }

    @Override
    public void deleteSemester(int courseId, int studentId) throws ServiceException {
        if (courseId <= 0 || studentId <= 0) {
            throw new ServiceException("Failed to delete semester: ids are negative");
        }
        try {
            boolean result = semesterDeleterDao.deleteSemester(courseId, studentId);
            if (!result) {
                throw new ServiceException("Failed to delete semester");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
