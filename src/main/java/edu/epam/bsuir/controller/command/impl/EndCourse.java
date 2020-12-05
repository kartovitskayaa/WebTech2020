package edu.epam.bsuir.controller.command.impl;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.controller.command.Command;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.dao.factory.impl.SqlDaoFactory;
import edu.epam.bsuir.service.bean.course.CourseEditorService;
import edu.epam.bsuir.service.bean.course.CourseGetterService;
import edu.epam.bsuir.service.bean.course.impl.CourseEditorServiceImpl;
import edu.epam.bsuir.service.bean.course.impl.CourseGetterServiceImpl;
import edu.epam.bsuir.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EndCourse implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("Role").equals("Lector")) {
            AbstractFactoryDao abstractFactoryDao = new SqlDaoFactory();
            CourseGetterService courseGetterService = new CourseGetterServiceImpl(abstractFactoryDao);
            CourseEditorService courseEditorService = new CourseEditorServiceImpl(abstractFactoryDao);

            int id = Integer.parseInt((String) request.getAttribute("courseId"));

            try {
                Course course = courseGetterService.getCourse(id);
                course.setFinished(course.isFinished());
                courseEditorService.setCourse(course);
                Command command = new VisitHomePage();
                command.execute(request, response);
            } catch (ServiceException ignored) {
            }
        }
    }
}
