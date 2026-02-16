<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Drivers - GreenFleet</title>
            <link rel="stylesheet" href="assets/css/style.css">
        </head>

        <body>
            <div class="dashboard-container">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="page" value="drivers" />
                </jsp:include>

                <div class="main-content">
                    <jsp:include page="header.jsp">
                        <jsp:param name="title" value="Driver Management" />
                    </jsp:include>

                    <!-- Add Driver Form -->
                    <div class="card" style="margin-bottom: 2rem;">
                        <h3>Register New Driver</h3>
                        <form action="drivers" method="post"
                            style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                            <div class="form-group">
                                <label>Full Name</label>
                                <input type="text" name="fullName" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" name="email">
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="tel" name="phone">
                            </div>
                            <div class="form-group">
                                <label>License Number</label>
                                <input type="text" name="licenseNumber" required>
                            </div>
                            <div class="form-group">
                                <label>License Expiry</label>
                                <input type="date" name="licenseExpiry" required>
                            </div>
                            <div class="form-group">
                                <label>Username (Login)</label>
                                <input type="text" name="username" required>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" required>
                            </div>
                            <div class="form-group" style="display: flex; align-items: flex-end;">
                                <button type="submit" class="btn-primary">Register Driver</button>
                            </div>
                        </form>
                    </div>

                    <!-- Driver Table -->
                    <div class="table-container">
                        <h3>Driver Roster</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>License</th>
                                    <th>Expiry</th>
                                    <th>Phone</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="d" items="${drivers}">
                                    <tr>
                                        <td>${d.driverName}</td>
                                        <td>${d.licenseNumber}</td>
                                        <td>${d.licenseExpiry}</td>
                                        <td>${d.phone}</td>
                                        <td><a href="#" style="color:var(--primary-color)">Edit</a></td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty drivers}">
                                    <tr>
                                        <td colspan="5" style="text-align:center">No drivers registered.</td>
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