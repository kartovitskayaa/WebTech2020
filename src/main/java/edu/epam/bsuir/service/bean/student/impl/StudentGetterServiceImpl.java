package edu.epam.bsuir.service.bean.student.impl;

import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.dao.bean.student.StudentGetterDao;
import edu.epam.bsuir.dao.exception.DAOException;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.service.bean.student.StudentGetterService;
import edu.epam.bsuir.service.exception.ServiceException;
import edu.epam.bsuir.service.util.AuthenticationUtil;

import java.util.List;

public class StudentGetterServiceImpl implements StudentGetterService {

    private final StudentGetterDao studentGetterDao;

    public StudentGetterServiceImpl(AbstractFactoryDao factoryDao) {
        studentGetterDao = factoryDao.getStudentGetterDao();
    }

    @Override
    public Student login(String name, String password) throws ServiceException {
        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            throw new ServiceException("Wrong input");
        }
        try {
            Student student = studentGetterDao.getStudentByName(name);
            if (student == null || !AuthenticationUtil.checkPassword(password, student.getPassword())) {
                throw new ServiceException("Failed to login");
            }

            return student;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Student getStudent(int id) throws ServiceException {
        if (id <= 0) {
            throw new ServiceException("Failed to validate: id is negative");
        }
        try {
            Student student = studentGetterDao.getStudent(id);
            if (student == null) {
                throw new ServiceException("Failed to get student");
            }

            return student;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getTotalStudentNumber() throws ServiceException {
        try {
            return studentGetterDao.getTotalStudentNumber();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Student> getSpecifiedNumberOfStudents(int from, int count) throws ServiceException {
        if (from < 0 || count <= 0) {
            throw new ServiceException("Wrong input");
        }
        try {
            List<Student> students = studentGetterDao.getSpecifiedNumberOfStudents(from, count);
            if (students.isEmpty()) {
                throw new ServiceException("Failed to get specified students");
            }
            return students;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
