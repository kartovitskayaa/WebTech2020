package edu.epam.bsuir.service.bean.course.impl;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.dao.bean.course.CourseEditorDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.course.CourseEditorService;
import edu.epam.bsuir.service.exception.ServiceException;
import edu.epam.bsuir.service.util.BeanValidator;

public class CourseEditorServiceImpl implements CourseEditorService {

    private final CourseEditorDao courseEditorDao;

    public CourseEditorServiceImpl(AbstractFactoryDao factoryDao) {
        this.courseEditorDao = factoryDao.getCourseEditorDao();
    }

    @Override
    public void setCourse(Course course) throws ServiceException {
        BeanValidator.validateCourse(course);
        try {
            boolean result = courseEditorDao.setCourse(course);
            if (!result) {
                throw new ServiceException("Failed to set course");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void addCourse(Course course) throws ServiceException {
        BeanValidator.validateCourse(course);
        try {
            int id = courseEditorDao.addCourse(course);
            if (id <= 0) {
                throw new ServiceException("Failed to add course: id is negative");
            }

            course.setId(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
