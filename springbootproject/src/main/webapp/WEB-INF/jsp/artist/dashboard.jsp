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
            <div class="row mt-4">
                <div class="col-md-6">
                    <div class="card mb-4">
                        <div class="card-header bg-secondary text-white">Media Gallery</div>
                        <div class="card-body">
                            <form action="/media/upload" method="post" class="mb-4">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="row g-2 align-items-center">
                                    <div class="col-sm-5">
                                        <input type="url" name="fileUrl" class="form-control" placeholder="Media URL"
                                            required>
                                    </div>
                                    <div class="col-sm-4">
                                        <select name="typeStr" class="form-select">
                                            <option value="IMAGE">Image</option>
                                            <option value="VIDEO">Video</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-3">
                                        <button type="submit" class="btn btn-primary w-100">Add</button>
                                    </div>
                                </div>
                            </form>
                            <div class="list-group">
                                <c:forEach var="media" items="${mediaList}">
                                    <div class="list-group-item d-flex justify-content-between align-items-center">
                                        <a href="${media.fileUrl}" target="_blank" class="text-truncate"
                                            style="max-width: 70%;">${media.fileUrl}</a>
                                        <span class="badge bg-secondary rounded-pill">${media.type}</span>
                                    </div>
                                </c:forEach>
                                <c:if test="${empty mediaList}">
                                    <p class="text-muted text-center mb-0">No media added yet.</p>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="card mb-4">
                        <div class="card-header bg-info text-white">Customer Reviews</div>
                        <div class="card-body p-0">
                            <ul class="list-group list-group-flush">
                                <c:forEach var="review" items="${reviews}">
                                    <li class="list-group-item">
                                        <div class="d-flex w-100 justify-content-between">
                                            <h6 class="mb-1">${review.customerName}</h6>
                                            <small class="text-muted">
                                                <c:forEach begin="1" end="${review.rating}">⭐</c:forEach>
                                            </small>
                                        </div>
                                        <p class="mb-1 small">${review.comment}</p>
                                    </li>
                                </c:forEach>
                                <c:if test="${empty reviews}">
                                    <li class="list-group-item text-muted text-center">No reviews yet.</li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="../common/footer.jsp" %>