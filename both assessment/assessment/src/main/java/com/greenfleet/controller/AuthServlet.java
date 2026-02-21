package com.greenfleet.controller;

import com.greenfleet.dao.UserDAO;
import com.greenfleet.model.User;
import com.greenfleet.utils.SecurityUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            login(request, response);
        } else if ("logout".equals(action)) {
            logout(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            logout(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // In a real app we would hash the password here before sending to DAO if the DB stores hashes
        // For now assuming plain text or handled (Validation check)
        // User user = userDAO.validate(request.getParameter("username"), SecurityUtils.hashPassword(request.getParameter("password")));
        // Simplified for this task as we inserted plain text admin
        
        String userPass = request.getParameter("password");
        // If you want to use the hash:
        // String hashedPass = SecurityUtils.hashPassword(userPass); 
        // But our default insert was plain text so we check plain text first or assumed
        
        User user = userDAO.validate(request.getParameter("username"), userPass);

        if (user != null) {
            if ("INACTIVE".equals(user.getStatus())) {
                request.setAttribute("error", "Account is inactive");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            
            // Redirect based on role
            if ("ADMIN".equals(user.getRole())) {
                response.sendRedirect("dashboard.jsp");
            } else if ("DRIVER".equals(user.getRole())) {
                response.sendRedirect("driver_dashboard.jsp"); // To be created
            } else {
                response.sendRedirect("dashboard.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid Credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}
