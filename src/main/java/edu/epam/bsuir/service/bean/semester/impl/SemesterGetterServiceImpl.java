package edu.epam.bsuir.service.bean.semester.impl;

import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.dao.bean.semester.SemesterGetterDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.semester.SemesterGetterService;
import edu.epam.bsuir.service.exception.ServiceException;

import java.util.List;

public class SemesterGetterServiceImpl implements SemesterGetterService {

    private final SemesterGetterDao semesterGetterDao;

    public SemesterGetterServiceImpl(AbstractFactoryDao factoryDao) {
        semesterGetterDao = factoryDao.getSemesterGetterDao();
    }

    @Override
    public Semester getSemester(int courseId, int studentId) throws ServiceException {
        if (courseId <= 0 || studentId <= 0) {
            throw new ServiceException("Failed to delete semester: ids are negative");
        }
        try {
            Semester semester = semesterGetterDao.getSemester(courseId, studentId);

            if (semester == null) {
                throw new ServiceException("Failed to get semester by it ids");
            }

            return semester;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getTotalSemesterNumber() throws ServiceException {
        try {
            return semesterGetterDao.getTotalSemesterNumber();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Semester> getSpecifiedNumberOfSemesters(int from, int count) throws ServiceException {
        if (from < 0 || count <= 0) {
            throw new ServiceException("Wrong input");
        }
        try {
            List<Semester> semesters = semesterGetterDao.getSpecifiedNumberOfSemesters(from, count);
            if (semesters.isEmpty()) {
                throw new ServiceException("Failed to get specified courses");
            }
            return semesters;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
