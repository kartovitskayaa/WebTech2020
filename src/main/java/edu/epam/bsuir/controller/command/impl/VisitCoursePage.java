package edu.epam.bsuir.controller.command.impl;

import edu.epam.bsuir.controller.command.Command;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.dao.factory.impl.SqlDaoFactory;
import edu.epam.bsuir.service.bean.course.CourseGetterService;
import edu.epam.bsuir.service.bean.course.impl.CourseGetterServiceImpl;
import edu.epam.bsuir.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VisitCoursePage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt((String) request.getAttribute("courseId"));

        AbstractFactoryDao abstractFactoryDao = new SqlDaoFactory();
        CourseGetterService courseGetterService = new CourseGetterServiceImpl(abstractFactoryDao);

        try {
            request.setAttribute("course", courseGetterService.getCourse(id));
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/LogIn.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException | ServletException | IOException ignored) {
        }
    }
}
