package edu.epam.bsuir.service.bean.course.impl;

import edu.epam.bsuir.dao.bean.course.CourseDeleterDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.course.CourseDeleterService;
import edu.epam.bsuir.service.exception.ServiceException;

public class CourseDeleterServiceImpl implements CourseDeleterService {

    private final CourseDeleterDao courseDeleterDao;

    public CourseDeleterServiceImpl(AbstractFactoryDao factoryDao) {
        courseDeleterDao = factoryDao.getCourseDeleterDao();
    }

    @Override
    public void deleteCourse(int id) throws ServiceException {
        if (id <= 0) {
            throw new ServiceException("Failed to delete course: id is negative");
        }
        try {
            boolean result = courseDeleterDao.deleteCourse(id);
            if (!result) {
                throw new ServiceException("Failed to delete course");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
