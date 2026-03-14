<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Dashboard - GreenFleet</title>
        <link rel="stylesheet" href="assets/css/style.css">
    </head>

    <body>
        <div class="dashboard-container">
            <jsp:include page="sidebar.jsp">
                <jsp:param name="page" value="dashboard" />
            </jsp:include>

            <div class="main-content">
                <jsp:include page="header.jsp">
                    <jsp:param name="title" value="Dashboard" />
                </jsp:include>

                <div class="cards-grid">
                    <div class="card">
                        <h3>Total Fleet Size</h3>
                        <div class="value">12</div>
                        <p style="color: #666; font-size: 0.8rem;">Vehicles Registered</p>
                    </div>
                    <div class="card">
                        <h3>Active Trips</h3>
                        <div class="value">4</div>
                        <p style="color: #666; font-size: 0.8rem;">Currently on road</p>
                    </div>
                    <div class="card">
                        <h3>Fuel Efficiency</h3>
                        <div class="value">4.5</div>
                        <p style="color: #666; font-size: 0.8rem;">Avg KM/Litre</p>
                    </div>
                    <div class="card">
                        <h3>Maintenance Alert</h3>
                        <div class="value" style="color: var(--danger-color)">2</div>
                        <p style="color: #666; font-size: 0.8rem;">Vehicles Needing Service</p>
                    </div>
                </div>

                <div class="table-container">
                    <h3>Recent Activity</h3>
                    <p>Welcome to the GreenFleet dashboard. Use the sidebar to manage vehicles, drivers, and trips.</p>
                    <!-- Placeholder for charts or recent logs -->
                    <div
                        style="height: 200px; display: flex; align-items: center; justify-content: center; background: #fafafa; border: 2px dashed #eee; color: #aaa;">
                        Chart Authorization Pending
                    </div>
                </div>
            </div>

            <jsp:include page="footer.jsp" />
        </div>
    </body>

    </html>