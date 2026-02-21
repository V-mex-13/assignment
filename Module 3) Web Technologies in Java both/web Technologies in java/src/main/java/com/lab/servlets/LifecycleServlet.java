package com.lab.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LifecycleServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println(">>> 1. init() called: Servlet is being initialized! This happens ONCE.");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>> 2. service()/doGet() called: Handling a client request.");
        resp.getWriter().println("Check your server console for lifecycle logs!");
    }
    @Override
    public void destroy() {
        System.out.println(">>> 3. destroy() called: Servlet is being taken out of service. This happens ONCE.");
    }
}
