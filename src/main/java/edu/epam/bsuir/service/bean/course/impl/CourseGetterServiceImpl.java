package edu.epam.bsuir.service.bean.course.impl;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.dao.bean.course.CourseGetterDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.course.CourseGetterService;
import edu.epam.bsuir.service.exception.ServiceException;

import java.util.List;

public class CourseGetterServiceImpl implements CourseGetterService {

    private final CourseGetterDao courseGetterDao;

    public CourseGetterServiceImpl(AbstractFactoryDao factoryDao) {
        courseGetterDao = factoryDao.getCourseGetterDao();
    }

    @Override
    public Course getCourse(int id) throws ServiceException {
        if (id <= 0) {
            throw new ServiceException("Failed to validate: id is negative");
        }
        try {
            Course course = courseGetterDao.getCourse(id);
            if (course == null) {
                throw new ServiceException("Failed to get course by it id(" + id + ")");
            }

            return course;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getTotalCourseNumber() throws ServiceException {
        try {
            return courseGetterDao.getTotalCourseNumber();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Course> getSpecifiedNumberOfCourses(int from, int count) throws ServiceException {
        if (from < 0 || count <= 0) {
            throw new ServiceException("Wrong input");
        }
        try {
            List<Course> courses = courseGetterDao.getSpecifiedNumberOfCourses(from, count);
            if (courses.isEmpty()) {
                throw new ServiceException("Failed to get specified courses");
            }

            return courses;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
