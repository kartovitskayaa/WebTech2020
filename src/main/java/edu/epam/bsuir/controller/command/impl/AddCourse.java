package edu.epam.bsuir.controller.command.impl;

import edu.epam.bsuir.bean.Course;
import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.controller.command.Command;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.dao.factory.impl.SqlDaoFactory;
import edu.epam.bsuir.service.bean.course.CourseEditorService;
import edu.epam.bsuir.service.bean.course.impl.CourseEditorServiceImpl;
import edu.epam.bsuir.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class AddCourse implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Timestamp startDate = Timestamp.valueOf(request.getParameter("startDate"));
        Timestamp endDate = Timestamp.valueOf(request.getParameter("endDate"));
        Lector lector = (Lector) request.getSession().getAttribute("User");

        Course course = new Course(name, false, startDate, endDate, lector);

        AbstractFactoryDao abstractFactoryDao = new SqlDaoFactory();
        CourseEditorService courseEditorService = new CourseEditorServiceImpl(abstractFactoryDao);

        try {
            courseEditorService.addCourse(course);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Home.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException | IOException | ServletException e) {
            request.setAttribute("error", true);
            Command command = new VisitAddCoursePage();
            command.execute(request, response);
        }
    }
}
