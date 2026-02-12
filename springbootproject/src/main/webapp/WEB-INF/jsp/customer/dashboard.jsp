<%@ include file="../common/header.jsp" %>
    <div class="container">
        <h2>Customer Dashboard</h2>
        <c:if test="${param.booked}">
            <div class="alert alert-success mt-3">Booking successful! The artist will review your request.</div>
        </c:if>
        <div class="row mt-4">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        My Bookings
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Booking ID</th>
                                    <th>Artist</th>
                                    <th>Event Date</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="booking" items="${bookings}">
                                    <tr>
                                        <td>${booking.id}</td>
                                        <td>${booking.artist.name}</td>
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