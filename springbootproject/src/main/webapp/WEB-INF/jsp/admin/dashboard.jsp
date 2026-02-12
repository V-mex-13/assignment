<%@ include file="../common/header.jsp" %>
    <div class="row">
        <div class="col-md-12">
            <h2 class="mb-4">Admin Dashboard</h2>

            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="artists-tab" data-bs-toggle="tab" data-bs-target="#artists"
                        type="button" role="tab" aria-controls="artists" aria-selected="true">Artists</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="bookings-tab" data-bs-toggle="tab" data-bs-target="#bookings"
                        type="button" role="tab" aria-controls="bookings" aria-selected="false">Bookings</button>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent">
                <!-- Artists Tab -->
                <div class="tab-pane fade show active p-3" id="artists" role="tabpanel" aria-labelledby="artists-tab">
                    <h4>All Artists</h4>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="artist" items="${artists}">
                                <tr>
                                    <td>${artist.id}</td>
                                    <td>${artist.name}</td>
                                    <td>
                                        <span
                                            class="badge ${artist.approvalStatus == 'APPROVED' ? 'bg-success' : (artist.approvalStatus == 'REJECTED' ? 'bg-danger' : 'bg-warning')}">
                                            ${artist.approvalStatus}
                                        </span>
                                    </td>
                                    <td>
                                        <div class="d-flex gap-2">
                                            <form action="/admin/approve-artist" method="post"
                                                onsubmit="return confirm('Are you sure you want to approve this artist?');">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <input type="hidden" name="id" value="${artist.id}" />
                                                <button type="submit" class="btn btn-sm btn-success">Approve</button>
                                            </form>
                                            <form action="/admin/reject-artist" method="post"
                                                onsubmit="return confirm('Are you sure you want to reject this artist?');">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <input type="hidden" name="id" value="${artist.id}" />
                                                <button type="submit" class="btn btn-sm btn-danger">Reject</button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Bookings Tab -->
                <div class="tab-pane fade p-3" id="bookings" role="tabpanel" aria-labelledby="bookings-tab">
                    <h4>All Bookings</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Artist</th>
                                <th>Customer</th>
                                <th>Event Date</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="booking" items="${bookings}">
                                <tr>
                                    <td>${booking.id}</td>
                                    <td>${booking.artist.name}</td>
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
    <!-- JS removed as actions are now form-based -->
    <%@ include file="../common/footer.jsp" %>