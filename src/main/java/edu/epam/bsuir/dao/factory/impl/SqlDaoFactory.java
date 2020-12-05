package edu.epam.bsuir.dao.factory.impl;

import edu.epam.bsuir.dao.bean.course.CourseDeleterDao;
import edu.epam.bsuir.dao.bean.course.CourseEditorDao;
import edu.epam.bsuir.dao.bean.course.CourseGetterDao;
import edu.epam.bsuir.dao.bean.lector.LectorEditorDao;
import edu.epam.bsuir.dao.bean.lector.LectorGetterDao;
import edu.epam.bsuir.dao.bean.student.StudentEditorDao;
import edu.epam.bsuir.dao.bean.student.StudentGetterDao;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.dao.bean.course.impl.CourseDeleterDaoImpl;
import edu.epam.bsuir.dao.bean.course.impl.CourseEditorDaoImpl;
import edu.epam.bsuir.dao.bean.course.impl.CourseGetterDaoImpl;
import edu.epam.bsuir.dao.bean.lector.impl.LectorEditorDaoImpl;
import edu.epam.bsuir.dao.bean.lector.impl.LectorGetterDaoImpl;
import edu.epam.bsuir.dao.bean.semester.*;
import edu.epam.bsuir.dao.bean.semester.impl.SemesterDeleterDaoImpl;
import edu.epam.bsuir.dao.bean.semester.impl.SemesterEditorDaoImpl;
import edu.epam.bsuir.dao.bean.semester.impl.SemesterGetterDaoImpl;
import edu.epam.bsuir.dao.bean.student.impl.StudentEditorDaoImpl;
import edu.epam.bsuir.dao.bean.student.impl.StudentGetterDaoImpl;

public class SqlDaoFactory implements AbstractFactoryDao {

    @Override
    public CourseGetterDao getCourseGetterDao() {
        return new CourseGetterDaoImpl();
    }

    @Override
    public CourseEditorDao getCourseEditorDao() {
        return new CourseEditorDaoImpl();
    }

    @Override
    public CourseDeleterDao getCourseDeleterDao() {
        return new CourseDeleterDaoImpl();
    }

    @Override
    public LectorGetterDao getLectorGetterDao() {
        return new LectorGetterDaoImpl();
    }

    @Override
    public LectorEditorDao getLectorEditorDao() {
        return new LectorEditorDaoImpl();
    }

    @Override
    public SemesterGetterDao getSemesterGetterDao() {
        return new SemesterGetterDaoImpl();
    }

    @Override
    public SemesterEditorDao getSemesterEditorDao() {
        return new SemesterEditorDaoImpl();
    }

    @Override
    public SemesterDeleterDao getSemesterDeleterDao() {
        return new SemesterDeleterDaoImpl();
    }

    @Override
    public StudentGetterDao getStudentGetterDao() {
        return new StudentGetterDaoImpl();
    }

    @Override
    public StudentEditorDao getStudentEditorDao() {
        return new StudentEditorDaoImpl();
    }
}
