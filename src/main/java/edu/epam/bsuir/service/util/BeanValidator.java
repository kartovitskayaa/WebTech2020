package edu.epam.bsuir.service.util;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.bean.Semester;
import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.service.exception.ServiceException;

public class BeanValidator {

    private BeanValidator(){
    }

    public static void validateSemester(Semester semester) throws ServiceException {
        if (semester == null) {
            throw new ServiceException("Failed to validate semester: semester is null");
        }
        if (semester.getMark() < 0) {
            throw new ServiceException("Failed to validate semester: mark is negative");
        }
        validateStudent(semester.getStudent());
        validateCourse(semester.getCourse());
    }

    public static void validateLector(Lector lector) throws ServiceException {
        if (lector == null) {
            throw new ServiceException("Failed to validate lector: lector is null");
        }
        if (lector.getId() < 0) {
            throw new ServiceException("Failed to validate lector: id is negative");
        }
        if (lector.getLogin() == null || lector.getLogin().isEmpty()) {
            throw new ServiceException("Failed to validate lector: login is empty");
        }
        if (lector.getPassword() == null || lector.getPassword().isEmpty()) {
            throw new ServiceException("Failed to validate lector: password is empty");
        }
    }

    public static void validateStudent(Student student) throws ServiceException {
        if (student == null) {
            throw new ServiceException("Failed to validate student: student is null");
        }
        if (student.getId() < 0) {
            throw new ServiceException("Failed to validate student: id is negative");
        }
        if (student.getLogin() == null || student.getLogin().isEmpty()) {
            throw new ServiceException("Failed to validate student: student is empty");
        }
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            throw new ServiceException("Failed to validate student: password is empty");
        }
        if (student.getAvgMark() < 0 || student.getAvgMark() > 10) {
            throw new ServiceException("Failed to validate student: average mark is outside the limit");
        }
    }

    public static void validateCourse(Course course) throws ServiceException {
        if (course == null) {
            throw new ServiceException("Failed to validate course: course is null");
        }
        if (course.getId() < 0) {
            throw new ServiceException("Failed to validate course: id is negative");
        }
        if (course.getName() == null || course.getName().isEmpty()) {
            throw new ServiceException("Failed to validate course: name is empty");
        }
        if (course.getStartDate() == null) {
            throw new ServiceException("Failed to validate course: start date is empty");
        }
        if (course.getEndDate() == null) {
            throw new ServiceException("Failed to validate course: end date is empty");
        }
        if (!course.getStartDate().equals(course.getEndDate()) &&
                course.getEndDate().before(course.getStartDate())) {
            throw new ServiceException("Failed to validate course: end date is before start date");
        }
    }
}
