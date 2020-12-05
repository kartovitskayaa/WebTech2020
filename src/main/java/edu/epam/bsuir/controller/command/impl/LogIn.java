package edu.epam.bsuir.controller.command.impl;

import edu.epam.bsuir.bean.Lector;
import edu.epam.bsuir.bean.Student;
import edu.epam.bsuir.controller.command.Command;
import edu.epam.bsuir.dao.factory.AbstractFactoryDao;
import edu.epam.bsuir.dao.factory.impl.SqlDaoFactory;
import edu.epam.bsuir.service.bean.lector.LectorGetterService;
import edu.epam.bsuir.service.bean.lector.impl.LectorGetterServiceImpl;
import edu.epam.bsuir.service.bean.student.StudentGetterService;
import edu.epam.bsuir.service.bean.student.impl.StudentGetterServiceImpl;
import edu.epam.bsuir.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogIn implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AbstractFactoryDao factoryDao = new SqlDaoFactory();

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        try {
            switch (request.getParameter("role")) {
                case "Student":
                    StudentGetterService studentGetterService = new StudentGetterServiceImpl(factoryDao);
                    Student student = studentGetterService.login(name, password);
                    request.getSession().setAttribute("Role", "Student");
                    request.getSession().setAttribute("User", student);
                    break;
                case "Lector":
                    LectorGetterService lectorGetterService = new LectorGetterServiceImpl(factoryDao);
                    Lector lector = lectorGetterService.login(name, password);
                    request.getSession().setAttribute("Role", "Lector");
                    request.getSession().setAttribute("User", lector);
                    break;
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Home.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException | IOException | ServletException e) {
            request.setAttribute("error", true);
            Command command = new VisitLogInPage();
            command.execute(request, response);
        }
    }
}
