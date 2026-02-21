<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - GreenFleet</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body class="login-body">
    <div class="login-container">
        <h2>GreenFleet Login</h2>
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <div class="alert error"><%= error %></div>
        <% } %>
        
        <form action="auth" method="post">
            <input type="hidden" name="action" value="login">
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required>
            </div>
            <button type="submit" class="btn-primary">Login</button>
        </form>
        <div class="login-footer">
            <p>EcoLogix Pvt. Ltd.</p>
        </div>
    </div>
</body>
</html>
