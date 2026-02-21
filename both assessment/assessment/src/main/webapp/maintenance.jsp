<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Maintenance - GreenFleet</title>
            <link rel="stylesheet" href="assets/css/style.css">
        </head>

        <body>
            <div class="dashboard-container">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="page" value="maintenance" />
                </jsp:include>

                <div class="main-content">
                    <jsp:include page="header.jsp">
                        <jsp:param name="title" value="Maintenance & Incidents" />
                    </jsp:include>

                    <!-- Add Log Form -->
                    <div class="card" style="margin-bottom: 2rem;">
                        <h3>Report Incident / Maintenance</h3>
                        <form action="maintenance" method="post"
                            style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                            <div class="form-group">
                                <label>Vehicle</label>
                                <select name="vehicleId" required>
                                    <c:forEach var="v" items="${vehicles}">
                                        <option value="${v.vehicleId}">${v.registrationNumber}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Type</label>
                                <select name="logType">
                                    <option value="MAINTENANCE">Regular Maintenance</option>
                                    <option value="ACCIDENT">Accident</option>
                                    <option value="BREAKDOWN">Breakdown</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Date</label>
                                <input type="date" name="logDate" required>
                            </div>
                            <div class="form-group">
                                <label>Cost</label>
                                <input type="number" step="0.01" name="cost" required>
                            </div>
                            <div class="form-group">
                                <label>Downtime (Hours)</label>
                                <input type="number" name="downtimeHours" required>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <select name="status">
                                    <option value="PENDING">Pending</option>
                                    <option value="COMPLETED">Completed</option>
                                </select>
                            </div>
                            <div class="form-group" style="grid-column: span 2;">
                                <label>Description</label>
                                <textarea name="description" rows="3"></textarea>
                            </div>
                            <div class="form-group"
                                style="grid-column: span 2; display: flex; justify-content: flex-end;">
                                <button type="submit" class="btn-primary">Submit Report</button>
                            </div>
                        </form>
                    </div>

                    <!-- Maintenance Table -->
                    <div class="table-container">
                        <h3>Log History</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Vehicle</th>
                                    <th>Type</th>
                                    <th>Description</th>
                                    <th>Cost</th>
                                    <th>Downtime</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="m" items="${logs}">
                                    <tr>
                                        <td>${m.logDate}</td>
                                        <td>${m.vehicleReg}</td>
                                        <td><span class="status-badge"
                                                style="background:#eee;color:#333">${m.logType}</span></td>
                                        <td>${m.description}</td>
                                        <td>$${m.cost}</td>
                                        <td>${m.downtimeHours} hrs</td>
                                        <td><span
                                                class="status-badge status-${m.status.toLowerCase()}">${m.status}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty logs}">
                                    <tr>
                                        <td colspan="7" style="text-align:center">No records found.</td>
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