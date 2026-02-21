package com.lab.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        ServletContext context = getServletContext();
        String adminEmail = config.getInitParameter("adminEmail");
        String dbUrl = context.getInitParameter("dbUrl");
        resp.getWriter().println("ServletConfig (Admin): " + adminEmail);
        resp.getWriter().println("ServletContext (DB URL): " + dbUrl);
    }
}
