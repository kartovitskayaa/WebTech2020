package edu.epam.bsuir.controller.servlet;

import edu.epam.bsuir.controller.command.Command;
import edu.epam.bsuir.controller.command.CommandFactory;
import edu.epam.bsuir.db.pool.impl.ElectiveConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet(name = "Controller", urlPatterns = "/Elective")
public class ControllerServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ControllerServlet.class);

    private static final String CLOSE_CONNECTION_ERROR = "Error during closing connections";
    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        Command command = CommandFactory.getInstance().receiveCommand(req.getParameter(COMMAND));

        if (command == null) {
            command = CommandFactory.getInstance().receiveCommand((String) req.getAttribute(COMMAND));

            if (command == null) {
                //command = new VisitHomePage();
            }
        }

        command.execute(req, resp);
    }

    @Override
    public void destroy() {
        try {
            ElectiveConnectionPool.CONNECTION_POOL_INSTANCE.closeAll();
        } catch (SQLException e) {
            LOGGER.fatal(CLOSE_CONNECTION_ERROR);
        }
    }
}
