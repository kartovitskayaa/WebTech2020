package edu.epam.bsuir.dao.factory;

import edu.epam.bsuir.dao.bean.course.CourseDeleterDao;
import edu.epam.bsuir.dao.bean.course.CourseEditorDao;
import edu.epam.bsuir.dao.bean.course.CourseGetterDao;
import edu.epam.bsuir.dao.bean.lector.LectorEditorDao;
import edu.epam.bsuir.dao.bean.lector.LectorGetterDao;
import edu.epam.bsuir.dao.bean.semester.SemesterDeleterDao;
import edu.epam.bsuir.dao.bean.semester.SemesterEditorDao;
import edu.epam.bsuir.dao.bean.semester.SemesterGetterDao;
import edu.epam.bsuir.dao.bean.student.StudentEditorDao;
import edu.epam.bsuir.dao.bean.student.StudentGetterDao;

public interface AbstractFactoryDao {

    CourseGetterDao getCourseGetterDao();
    CourseEditorDao getCourseEditorDao();
    CourseDeleterDao getCourseDeleterDao();

    LectorGetterDao getLectorGetterDao();
    LectorEditorDao getLectorEditorDao();

    SemesterGetterDao getSemesterGetterDao();
    SemesterEditorDao getSemesterEditorDao();
    SemesterDeleterDao getSemesterDeleterDao();

    StudentGetterDao getStudentGetterDao();
    StudentEditorDao getStudentEditorDao();
}
