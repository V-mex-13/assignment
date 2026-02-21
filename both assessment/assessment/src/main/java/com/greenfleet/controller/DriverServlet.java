package com.greenfleet.controller;

import com.greenfleet.dao.DriverDAO;
import com.greenfleet.dao.UserDAO;
import com.greenfleet.model.Driver;
import com.greenfleet.model.User;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/drivers")
public class DriverServlet extends HttpServlet {
    private DriverDAO driverDAO;
    private UserDAO userDAO;

    public void init() {
        driverDAO = new DriverDAO();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Driver> driverList = driverDAO.getAllDrivers();
        request.setAttribute("drivers", driverList);
        request.getRequestDispatcher("drivers.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create User first
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username"); // Ideally ensure unique
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String license = request.getParameter("licenseNumber");
        Date expiry = Date.valueOf(request.getParameter("licenseExpiry"));

        User user = new User(0, username, password, "DRIVER", fullName, email, "ACTIVE");
        if (userDAO.addUser(user)) {
             // user.getUserId() is now populated if generated keys worked in DAO
             Driver driver = new Driver(0, user.getUserId(), license, expiry, phone);
             driverDAO.addDriver(driver);
        }
        
        response.sendRedirect("drivers");
    }
}
