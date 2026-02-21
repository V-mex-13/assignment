package com.lab.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class SessionLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String rem = req.getParameter("remember");

        HttpSession session = req.getSession(true);
        session.setAttribute("loggedInUser", user);
        
        if (rem != null) {
            Cookie c = new Cookie("savedUser", user);
            c.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(c);
        }
        
        resp.sendRedirect("dashboard.jsp");
    }
}
