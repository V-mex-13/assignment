<%@ include file="../common/header.jsp" %>
    <div class="container">
        <h2>Artist Dashboard</h2>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">My Profile</h5>
                        <p><strong>Name:</strong> ${artist.name}</p>
                        <p><strong>Category:</strong> ${artist.category}</p>
                        <p><strong>Experience:</strong> ${artist.experienceYears} years</p>
                        <p><strong>Price/Event:</strong> $${artist.pricePerEvent}</p>
                        <p><strong>Status:</strong>
                            <span class="badge ${artist.approvalStatus == 'APPROVED' ? 'bg-success' : 'bg-warning'}">
                                ${artist.approvalStatus}
                            </span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        My Bookings
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Booking ID</th>
                                    <th>Customer</th>
                                    <th>Event Date</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="booking" items="${bookings}">
                                    <tr>
                                        <td>${booking.id}</td>
                                        <td>${booking.customer.name}</td>
                                        <td>${booking.eventDate}</td>
                                        <td>${booking.status}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../common/footer.jsp" %>