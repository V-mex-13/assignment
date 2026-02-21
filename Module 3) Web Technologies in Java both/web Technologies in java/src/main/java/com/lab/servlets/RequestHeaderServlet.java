package com.lab.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>HTTP Request Headers</title></head><body>");
        out.println("<h2>Your Browser's Request Headers</h2>");
        out.println("<table border='1' style='border-collapse: collapse; padding: 5px;'>");
        out.println("<tr style='background-color: #f2f2f2;'><th>Header Name</th><th>Header Value</th></tr>");
        
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            out.println("<tr><td><strong>" + headerName + "</strong></td><td>" + headerValue + "</td></tr>");
        }
        out.println("</table></body></html>");
    }
}
