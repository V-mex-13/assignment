<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Fuel Logs - GreenFleet</title>
            <link rel="stylesheet" href="assets/css/style.css">
        </head>

        <body>
            <div class="dashboard-container">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="page" value="fuel" />
                </jsp:include>

                <div class="main-content">
                    <jsp:include page="header.jsp">
                        <jsp:param name="title" value="Fuel Management" />
                    </jsp:include>

                    <!-- Add Fuel Log Form -->
                    <div class="card" style="margin-bottom: 2rem;">
                        <h3>Log Fuel Entry</h3>
                        <form action="fuel" method="post"
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
                                <label>Driver</label>
                                <select name="driverId" required>
                                    <c:forEach var="d" items="${drivers}">
                                        <option value="${d.driverId}">${d.driverName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Date & Time</label>
                                <input type="datetime-local" name="fillUpDate" required>
                            </div>
                            <div class="form-group">
                                <label>Location (Station)</label>
                                <input type="text" name="location">
                            </div>
                            <div class="form-group">
                                <label>Amount (Litres)</label>
                                <input type="number" step="0.1" name="amountLitres" required>
                            </div>
                            <div class="form-group">
                                <label>Cost / Litre</label>
                                <input type="number" step="0.01" name="costPerLitre" required>
                            </div>
                            <div class="form-group">
                                <label>Odometer Reading (km)</label>
                                <input type="number" step="0.1" name="odometerReading" required>
                            </div>
                            <div class="form-group" style="display: flex; align-items: flex-end;">
                                <button type="submit" class="btn-primary">Save Log</button>
                            </div>
                        </form>
                    </div>

                    <!-- Fuel Table -->
                    <div class="table-container">
                        <div style="display:flex; justify-content:space-between; align-items:center;">
                            <h3>Consumption History</h3>
                            <a href="fuel?action=export" class="btn-primary"
                                style="text-decoration:none; padding: 0.5rem 1rem; font-size: 0.9rem;">Export CSV</a>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Vehicle</th>
                                    <th>Driver</th>
                                    <th>Litres</th>
                                    <th>Total Cost</th>
                                    <th>Odometer</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="f" items="${logs}">
                                    <tr>
                                        <td>${f.fillUpDate}</td>
                                        <td>${f.vehicleReg}</td>
                                        <td>${f.driverName}</td>
                                        <td>${f.amountLitres} L</td>
                                        <td>$${f.totalCost}</td>
                                        <td>${f.odometerReading} km</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty logs}">
                                    <tr>
                                        <td colspan="6" style="text-align:center">No fuel logs found.</td>
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