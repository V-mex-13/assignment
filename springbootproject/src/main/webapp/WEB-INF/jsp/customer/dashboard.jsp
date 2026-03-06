<%@ include file="../common/header.jsp" %>
    <div class="row mb-4">
        <div class="col-md-12">
            <div
                class="d-flex justify-content-between align-items-center bg-white p-3 rounded shadow-sm border-start border-success border-4">
                <h2 class="mb-0 fw-bold text-dark"><i class="fa-solid fa-user-check text-success me-2"></i>Customer
                    Dashboard</h2>
                <span class="badge bg-success text-white fs-6 py-2 px-3 rounded-pill"><i
                        class="fa-solid fa-hand-wave me-2"></i>Welcome, ${customer.name}</span>
            </div>
        </div>
    </div>

    <c:if test="${param.booked}">
        <div class="alert alert-success alert-dismissible fade show shadow-sm rounded-4 border-0" role="alert">
            <i class="fa-solid fa-circle-check fs-5 me-2 align-middle"></i>
            <strong>Booking successful!</strong> The artist will review your request.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <div class="row">
        <div class="col-md-12">
            <div class="card shadow-sm border-0 rounded-4 mb-4">
                <div class="card-header bg-white border-bottom-0 pt-4 pb-2">
                    <h5 class="card-title fw-bold text-secondary mb-0"><i
                            class="fa-regular fa-calendar-check text-primary me-2"></i>My Bookings</h5>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover align-middle mb-0">
                            <thead class="table-light text-muted">
                                <tr>
                                    <th class="ps-4 pb-3 fw-semibold">Booking ID</th>
                                    <th class="pb-3 fw-semibold">Artist</th>
                                    <th class="pb-3 fw-semibold">Event Date</th>
                                    <th class="pb-3 fw-semibold">Status</th>
                                    <th class="pe-4 pb-3 fw-semibold text-center">Leave a Review</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="booking" items="${bookings}">
                                    <tr>
                                        <td class="ps-4 text-muted fw-medium">#${booking.id}</td>
                                        <td class="fw-bold"><i
                                                class="fa-solid fa-palette text-info me-2"></i>${booking.artist.name}
                                        </td>
                                        <td><i class="fa-regular fa-calendar text-muted me-2"></i>${booking.eventDate}
                                        </td>
                                        <td>
                                            <span
                                                class="badge bg-secondary rounded-pill px-2 py-1">${booking.status}</span>
                                        </td>
                                        <td class="pe-4">
                                            <form action="/review/submit" method="post"
                                                class="d-flex align-items-center justify-content-center gap-2">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <input type="hidden" name="artistId" value="${booking.artist.id}">

                                                <select name="rating"
                                                    class="form-select form-select-sm border-0 shadow-sm rounded-pill bg-light"
                                                    style="width: 80px;">
                                                    <option value="5">5 ⭐</option>
                                                    <option value="4">4 ⭐</option>
                                                    <option value="3">3 ⭐</option>
                                                    <option value="2">2 ⭐</option>
                                                    <option value="1">1 ⭐</option>
                                                </select>

                                                <input type="text" name="comment"
                                                    class="form-control form-control-sm border-0 shadow-sm rounded-pill bg-light"
                                                    placeholder="Short review..." required style="min-width: 150px;">

                                                <button type="submit"
                                                    class="btn btn-sm btn-primary rounded-pill shadow-sm fw-bold px-3">
                                                    <i class="fa-regular fa-paper-plane me-1"></i>Send
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty bookings}">
                                    <tr>
                                        <td colspan="5" class="text-center text-muted py-5">You haven't made any
                                            bookings yet.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../common/footer.jsp" %>