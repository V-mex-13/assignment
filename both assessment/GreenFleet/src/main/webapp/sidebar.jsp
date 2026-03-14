<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="sidebar">
        <h3>GreenFleet</h3>
        <ul>
            <li><a href="dashboard" class="${param.page == 'dashboard' ? 'active' : ''}">Dashboard</a></li>
            <li><a href="vehicles" class="${param.page == 'vehicles' ? 'active' : ''}">Vehicles</a></li>
            <li><a href="drivers" class="${param.page == 'drivers' ? 'active' : ''}">Drivers</a></li>
            <li><a href="trips" class="${param.page == 'trips' ? 'active' : ''}">Trip Scheduler</a></li>
            <li><a href="fuel" class="${param.page == 'fuel' ? 'active' : ''}">Fuel Logs</a></li>
            <li><a href="maintenance" class="${param.page == 'maintenance' ? 'active' : ''}">Maintenance</a></li>
            <li><a href="auth?action=logout">Logout</a></li>
        </ul>
    </div>