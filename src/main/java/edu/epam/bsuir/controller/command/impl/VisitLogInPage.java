package edu.epam.bsuir.controller.command.impl;

import edu.epam.bsuir.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VisitLogInPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/LogIn.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ignored) {
        }
    }
}
