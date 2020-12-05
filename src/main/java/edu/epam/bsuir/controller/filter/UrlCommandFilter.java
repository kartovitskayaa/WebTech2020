package edu.epam.bsuir.controller.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "UrlCommand", urlPatterns = "/*")
public class UrlCommandFilter extends HttpFilter {

    private static final Set<String> BUSINESS_URLS = new HashSet<>();

    private static final String COMMAND_ATTRIBUTE = "command";
    private static final String SERVLET_PATH = "/Elective";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String uri = request.getRequestURI().substring(request.getContextPath().length());

        if (BUSINESS_URLS.contains(uri)) {
            if (request.getAttribute(COMMAND_ATTRIBUTE) == null) {
                request.setAttribute(COMMAND_ATTRIBUTE, uri);
            }
            request.getRequestDispatcher(SERVLET_PATH).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
