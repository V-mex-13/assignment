<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Vehicles - GreenFleet</title>
            <link rel="stylesheet" href="assets/css/style.css">
        </head>

        <body>
            <div class="dashboard-container">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="page" value="vehicles" />
                </jsp:include>

                <div class="main-content">
                    <jsp:include page="header.jsp">
                        <jsp:param name="title" value="Vehicle Management" />
                    </jsp:include>

                    <!-- Add Vehicle Form -->
                    <div class="card" style="margin-bottom: 2rem;">
                        <h3>Add New Vehicle</h3>
                        <form action="vehicles" method="post"
                            style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                            <div class="form-group">
                                <label>Registration Number</label>
                                <input type="text" name="registrationNumber" required
                                    placeholder="Type Registration Number">
                            </div>
                            <div class="form-group">
                                <label>Model</label>
                                <input type="text" name="model" required placeholder="Type Model">
                            </div>
                            <div class="form-group">
                                <label>Type</label>
                                <select name="type">
                                    <option value="Truck">Truck</option>
                                    <option value="Van">Van</option>
                                    <option value="Lorry">Lorry</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Capacity (Tons)</label>
                                <input type="number" step="0.1" name="capacityTons" required>
                            </div>
                            <div class="form-group">
                                <label>Fuel Type</label>
                                <select name="fuelType">
                                    <option value="Diesel">Diesel</option>
                                    <option value="Petrol">Petrol</option>
                                    <option value="Electric">Electric</option>
                                </select>
                            </div>
                            <div class="form-group" style="display: flex; align-items: flex-end;">
                                <button type="submit" class="btn-primary">Add Vehicle</button>
                            </div>
                        </form>
                    </div>

                    <!-- Vehicle Table -->
                    <div class="table-container">
                        <h3>Fleet Inventory</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>Reg Number</th>
                                    <th>Model</th>
                                    <th>Type</th>
                                    <th>Capacity</th>
                                    <th>Fuel</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="v" items="${vehicles}">
                                    <tr>
                                        <td>${v.registrationNumber}</td>
                                        <td>${v.model}</td>
                                        <td>${v.type}</td>
                                        <td>${v.capacityTons} tons</td>
                                        <td>${v.fuelType}</td>
                                        <td><span
                                                class="status-badge status-${v.status.toLowerCase()}">${v.status}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty vehicles}">
                                    <tr>
                                        <td colspan="6" style="text-align:center">No vehicles found.</td>
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