<%@ include file="../common/header.jsp" %>
    <div class="row mb-4">
        <div class="col-md-12">
            <div
                class="d-flex justify-content-between align-items-center bg-white p-3 rounded shadow-sm border-start border-primary border-4">
                <h2 class="mb-0 fw-bold text-dark"><i class="fa-solid fa-user-shield text-primary me-2"></i>Admin
                    Dashboard</h2>
                <span class="badge bg-primary fs-6 py-2 px-3 rounded-pill text-white"><i
                        class="fa-solid fa-calendar-check me-2"></i>Manage Portal</span>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="card shadow-sm border-0 rounded-4 overflow-hidden">
                <div class="card-header bg-white border-bottom-0 pt-4 pb-0">
                    <ul class="nav nav-pills nav-fill gap-2 p-1 bg-light rounded-pill" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active rounded-pill fw-semibold" id="artists-tab"
                                data-bs-toggle="tab" data-bs-target="#artists" type="button" role="tab"
                                aria-controls="artists" aria-selected="true">
                                <i class="fa-solid fa-users me-2"></i>Artists
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link rounded-pill fw-semibold text-dark" id="bookings-tab"
                                data-bs-toggle="tab" data-bs-target="#bookings" type="button" role="tab"
                                aria-controls="bookings" aria-selected="false">
                                <i class="fa-solid fa-calendar-days me-2"></i>Bookings
                            </button>
                        </li>
                    </ul>
                </div>

                <div class="card-body p-4">
                    <div class="tab-content" id="myTabContent">
                        <!-- Artists Tab -->
                        <div class="tab-pane fade show active" id="artists" role="tabpanel"
                            aria-labelledby="artists-tab">
                            <h4 class="mb-4 fw-bold text-secondary border-bottom pb-2">All Artists</h4>
                            <div class="table-responsive">
                                <table class="table table-hover align-middle">
                                    <thead class="table-light text-muted">
                                        <tr>
                                            <th class="fw-semibold pb-3">ID</th>
                                            <th class="fw-semibold pb-3">Name</th>
                                            <th class="fw-semibold pb-3">Status</th>
                                            <th class="fw-semibold pb-3 text-end">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="artist" items="${artists}">
                                            <tr>
                                                <td class="fw-medium text-muted">#${artist.id}</td>
                                                <td class="fw-bold">${artist.name}</td>
                                                <td>
                                                    <span
                                                        class="badge rounded-pill px-3 py-2 ${artist.approvalStatus == 'APPROVED' ? 'bg-success' : (artist.approvalStatus == 'REJECTED' ? 'bg-danger' : 'bg-warning text-dark')}">
                                                        <i
                                                            class="fa-solid ${artist.approvalStatus == 'APPROVED' ? 'fa-check' : (artist.approvalStatus == 'REJECTED' ? 'fa-xmark' : 'fa-clock')} me-1"></i>
                                                        ${artist.approvalStatus}
                                                    </span>
                                                </td>
                                                <td class="text-end">
                                                    <div class="d-flex gap-2 justify-content-end">
                                                        <form action="/admin/approve-artist" method="post"
                                                            onsubmit="return confirm('Approve this artist?');">
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                value="${_csrf.token}" />
                                                            <input type="hidden" name="id" value="${artist.id}" />
                                                            <button type="submit"
                                                                class="btn btn-sm btn-outline-success fw-bold rounded-pill px-3"><i
                                                                    class="fa-solid fa-check me-1"></i>Approve</button>
                                                        </form>
                                                        <form action="/admin/reject-artist" method="post"
                                                            onsubmit="return confirm('Reject this artist?');">
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                value="${_csrf.token}" />
                                                            <input type="hidden" name="id" value="${artist.id}" />
                                                            <button type="submit"
                                                                class="btn btn-sm btn-outline-danger fw-bold rounded-pill px-3"><i
                                                                    class="fa-solid fa-ban me-1"></i>Reject</button>
                                                        </form>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty artists}">
                                            <tr>
                                                <td colspan="4" class="text-center text-muted py-4">No artists found.
                                                </td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- Bookings Tab -->
                        <div class="tab-pane fade" id="bookings" role="tabpanel" aria-labelledby="bookings-tab">
                            <h4 class="mb-4 fw-bold text-secondary border-bottom pb-2">All Bookings</h4>
                            <div class="table-responsive">
                                <table class="table table-hover align-middle">
                                    <thead class="table-light text-muted">
                                        <tr>
                                            <th class="fw-semibold pb-3">Booking ID</th>
                                            <th class="fw-semibold pb-3">Artist</th>
                                            <th class="fw-semibold pb-3">Customer</th>
                                            <th class="fw-semibold pb-3">Event Date</th>
                                            <th class="fw-semibold pb-3">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="booking" items="${bookings}">
                                            <tr>
                                                <td class="fw-medium text-muted">#${booking.id}</td>
                                                <td class="fw-bold"><i
                                                        class="fa-solid fa-palette text-primary me-2"></i>${booking.artist.name}
                                                </td>
                                                <td class="fw-bold"><i
                                                        class="fa-solid fa-user text-info me-2"></i>${booking.customer.name}
                                                </td>
                                                <td><i
                                                        class="fa-regular fa-calendar text-muted me-2"></i>${booking.eventDate}
                                                </td>
                                                <td>
                                                    <span
                                                        class="badge rounded-pill bg-secondary px-3 py-2">${booking.status}</span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty bookings}">
                                            <tr>
                                                <td colspan="5" class="text-center text-muted py-4">No bookings found.
                                                </td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <style>
        .nav-pills .nav-link.active,
        .nav-pills .show>.nav-link {
            background: linear-gradient(45deg, #0d6efd, #0dcaf0);
            color: white !important;
            box-shadow: 0 4px 6px rgba(13, 110, 253, 0.2);
        }
    </style>

    <%@ include file="../common/footer.jsp" %>