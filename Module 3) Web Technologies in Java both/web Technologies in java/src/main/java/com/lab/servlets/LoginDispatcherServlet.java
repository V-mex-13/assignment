package com.lab.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginDispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if ("admin".equals(user) && "1234".equals(pass)) {
            req.setAttribute("user", user);
            RequestDispatcher rd = req.getRequestDispatcher("/dashboard.jsp");
            rd.forward(req, resp);
        } else {
            resp.getWriter().println("<h3 style='color:red;'>Invalid Credentials</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/index.html");
            rd.include(req, resp);
        }
    }
}
