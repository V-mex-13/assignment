package com.greenfleet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // In real app, fetch statistics here (vehicle count, trip count etc.)
        // For now, static placeholders in JSP are fine, or we can fetch simple counts
        
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
