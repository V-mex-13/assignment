<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Trips - GreenFleet</title>
            <link rel="stylesheet" href="assets/css/style.css">
        </head>

        <body>
            <div class="dashboard-container">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="page" value="trips" />
                </jsp:include>

                <div class="main-content">
                    <jsp:include page="header.jsp">
                        <jsp:param name="title" value="Route & Trip Scheduler" />
                    </jsp:include>

                    <!-- Schedule Trip Form -->
                    <div class="card" style="margin-bottom: 2rem;">
                        <h3>Schedule New Trip</h3>
                        <form action="trips" method="post"
                            style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                            <div class="form-group">
                                <label>Select Vehicle</label>
                                <select name="vehicleId" required>
                                    <c:forEach var="v" items="${vehicles}">
                                        <option value="${v.vehicleId}">${v.registrationNumber} (${v.model})</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Select Driver</label>
                                <select name="driverId" required>
                                    <c:forEach var="d" items="${drivers}">
                                        <option value="${d.driverId}">${d.driverName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Start Location</label>
                                <input type="text" name="startLocation" required>
                            </div>
                            <div class="form-group">
                                <label>End Location</label>
                                <input type="text" name="endLocation" required>
                            </div>
                            <div class="form-group">
                                <label>Departure Time</label>
                                <input type="datetime-local" name="startTime" required>
                            </div>
                            <div class="form-group" style="display: flex; align-items: flex-end;">
                                <button type="submit" class="btn-primary">Schedule Trip</button>
                            </div>
                        </form>
                    </div>

                    <!-- Trips Table -->
                    <div class="table-container">
                        <h3>Scheduled Trips</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>Vehicle</th>
                                    <th>Driver</th>
                                    <th>Route</th>
                                    <th>Departure</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="t" items="${trips}">
                                    <tr>
                                        <td>${t.vehicleReg}</td>
                                        <td>${t.driverName}</td>
                                        <td>${t.startLocation} &rarr; ${t.endLocation}</td>
                                        <td>${t.startTime}</td>
                                        <td><span
                                                class="status-badge status-${t.status.toLowerCase()}">${t.status}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty trips}">
                                    <tr>
                                        <td colspan="5" style="text-align:center">No trips scheduled.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>

                    <jsp:include page="footer.jsp" />
                </div>
            </div>
        </body>

        </html>