package edu.epam.bsuir.service.bean.student.impl;

import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.dao.bean.student.StudentEditorDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.student.StudentEditorService;
import edu.epam.bsuir.service.exception.ServiceException;
import edu.epam.bsuir.service.util.AuthenticationUtil;
import edu.epam.bsuir.service.util.BeanValidator;

public class StudentEditorServiceImpl implements StudentEditorService {

    private final StudentEditorDao studentEditorDao;

    public StudentEditorServiceImpl(AbstractFactoryDao factoryDao) {
        studentEditorDao = factoryDao.getStudentEditorDao();
    }

    @Override
    public void setStudent(Student student) throws ServiceException {
        BeanValidator.validateStudent(student);
        try {
            boolean result = studentEditorDao.setStudent(student);
            if (!result) {
                throw new ServiceException("Failed to set student");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void signUpStudent(Student student) throws ServiceException {
        BeanValidator.validateStudent(student);
        try {
            student.setPassword(AuthenticationUtil.getHash(student.getPassword()));
            int id = studentEditorDao.addStudent(student);
            if (id <= 0) {
                throw new ServiceException("Failed to add student: id is negative");
            }

            student.setId(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
