package com.lab.filters;

import jakarta.servlet.*;
import java.io.IOException;

public class ValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        String user = request.getParameter("username");
        
        if (user == null || user.trim().isEmpty()) {
            response.getWriter().println("<html><body><h3>Validation Error: Username cannot be empty!</h3></body></html>");
            return;
        }
        
        chain.doFilter(request, response);
    }
}
