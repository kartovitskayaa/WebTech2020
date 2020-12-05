package edu.epam.bsuir.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This {@code Command} interface provides
 * with ability to execute various commands
 * using {@code HttpServletRequest} and {@code HttpServletResponse}.
 *
 * @author Maria Kartovitskaya
 */
public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response);
}
