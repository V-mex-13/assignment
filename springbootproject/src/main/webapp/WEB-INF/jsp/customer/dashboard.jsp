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
                                    <th>Action (Review)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="booking" items="${bookings}">
                                    <tr>
                                        <td>${booking.id}</td>
                                        <td>${booking.artist.name}</td>
                                        <td>${booking.eventDate}</td>
                                        <td>${booking.status}</td>
                                        <td>
                                            <form action="/review/submit" method="post"
                                                class="d-flex align-items-center gap-2">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <input type="hidden" name="artistId" value="${booking.artist.id}">
                                                <select name="rating" class="form-select form-select-sm"
                                                    style="width: 70px;">
                                                    <option value="5">5 ⭐</option>
                                                    <option value="4">4 ⭐</option>
                                                    <option value="3">3 ⭐</option>
                                                    <option value="2">2 ⭐</option>
                                                    <option value="1">1 ⭐</option>
                                                </select>
                                                <input type="text" name="comment" class="form-control form-control-sm"
                                                    placeholder="Short review..." required>
                                                <button type="submit" class="btn btn-sm btn-primary">Send</button>
                                            </form>
                                        </td>
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