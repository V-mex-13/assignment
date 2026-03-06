<%@ include file="../common/header.jsp" %>
    <div class="row mb-4">
        <div class="col-md-12">
            <div
                class="d-flex justify-content-between align-items-center bg-white p-3 rounded shadow-sm border-start border-info border-4">
                <h2 class="mb-0 fw-bold text-dark"><i class="fa-solid fa-palette text-info me-2"></i>Artist Dashboard
                </h2>
                <span class="badge bg-info text-dark fs-6 py-2 px-3 rounded-pill"><i
                        class="fa-solid fa-star me-2"></i>Welcome, ${artist.name}</span>
            </div>
        </div>
    </div>

    <div class="row">
        <!-- Profile Card -->
        <div class="col-lg-4 col-md-5 mb-4">
            <div class="card shadow-sm border-0 rounded-4 h-100">
                <div class="card-header bg-white border-bottom-0 pt-4 pb-2">
                    <h5 class="card-title fw-bold text-secondary mb-0"><i
                            class="fa-solid fa-id-badge text-primary me-2"></i>My Profile</h5>
                </div>
                <div class="card-body">
                    <div class="text-center mb-4">
                        <img src="${empty artist.profileImage ? 'https://ui-avatars.com/api/?name=' += artist.name += '&background=random' : '/uploads/' += artist.profileImage}"
                            alt="Profile" class="rounded-circle shadow-sm border border-3 border-info"
                            style="width: 100px; height: 100px; object-fit: cover;">
                        <h5 class="fw-bold mt-3 mb-1">${artist.name}</h5>
                        <p class="text-muted mb-0">${artist.category}</p>
                        <div class="mt-2">
                            <span
                                class="badge rounded-pill px-3 py-2 ${artist.approvalStatus == 'APPROVED' ? 'bg-success' : (artist.approvalStatus == 'REJECTED' ? 'bg-danger' : 'bg-warning text-dark')}">
                                <i
                                    class="fa-solid ${artist.approvalStatus == 'APPROVED' ? 'fa-check' : (artist.approvalStatus == 'REJECTED' ? 'fa-xmark' : 'fa-clock')} me-1"></i>
                                ${artist.approvalStatus}
                            </span>
                        </div>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item d-flex justify-content-between align-items-center px-0 py-3">
                            <span class="text-muted"><i class="fa-solid fa-briefcase me-2"></i>Experience</span>
                            <span class="fw-semibold">${artist.experienceYears} Years</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center px-0 py-3">
                            <span class="text-muted"><i class="fa-solid fa-money-bill-wave me-2"></i>Price/Event</span>
                            <span class="fw-semibold text-success">$${artist.pricePerEvent}</span>
                        </li>
                        <li class="list-group-item px-0 py-3">
                            <span class="text-muted d-block mb-1"><i class="fa-solid fa-align-left me-2"></i>Bio</span>
                            <p class="mb-0 small text-dark">${artist.description != null ? artist.description : 'No
                                biography provided.'}</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Right Column -->
        <div class="col-lg-8 col-md-7">
            <!-- Bookings Card -->
            <div class="card shadow-sm border-0 rounded-4 mb-4">
                <div class="card-header bg-white border-bottom-0 pt-4 pb-2">
                    <h5 class="card-title fw-bold text-secondary mb-0"><i
                            class="fa-regular fa-calendar-check text-success me-2"></i>My Bookings</h5>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover align-middle mb-0">
                            <thead class="table-light text-muted">
                                <tr>
                                    <th class="ps-4">ID</th>
                                    <th>Customer</th>
                                    <th>Event Date</th>
                                    <th class="pe-4">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="booking" items="${bookings}">
                                    <tr>
                                        <td class="ps-4 text-muted fw-medium">#${booking.id}</td>
                                        <td class="fw-bold"><i
                                                class="fa-solid fa-user-circle text-info me-2"></i>${booking.customer.name}
                                        </td>
                                        <td><i class="fa-regular fa-calendar text-muted me-2"></i>${booking.eventDate}
                                        </td>
                                        <td class="pe-4">
                                            <span
                                                class="badge bg-secondary rounded-pill px-2 py-1">${booking.status}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty bookings}">
                                    <tr>
                                        <td colspan="4" class="text-center text-muted py-4">No bookings yet.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row">
                <!-- Media Gallery -->
                <div class="col-md-6 mb-4">
                    <div class="card shadow-sm border-0 rounded-4 h-100">
                        <div
                            class="card-header bg-white border-bottom-0 pt-3 pb-2 d-flex justify-content-between align-items-center">
                            <h6 class="card-title fw-bold text-secondary mb-0"><i
                                    class="fa-solid fa-images text-primary me-2"></i>Media Gallery</h6>
                        </div>
                        <div class="card-body">
                            <form action="/media/upload" method="post" class="mb-3 bg-light p-3 rounded-3">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="mb-2">
                                    <input type="url" name="fileUrl"
                                        class="form-control form-control-sm border-0 shadow-sm"
                                        placeholder="Media URL (e.g. YouTube, Imgur)" required>
                                </div>
                                <div class="d-flex gap-2">
                                    <select name="typeStr" class="form-select form-select-sm border-0 shadow-sm w-50">
                                        <option value="IMAGE">Image</option>
                                        <option value="VIDEO">Video</option>
                                    </select>
                                    <button type="submit" class="btn btn-primary btn-sm w-50 fw-bold shadow-sm"><i
                                            class="fa-solid fa-plus me-1"></i>Add</button>
                                </div>
                            </form>

                            <div class="list-group list-group-flush mt-2" style="max-height: 200px; overflow-y: auto;">
                                <c:forEach var="media" items="${mediaList}">
                                    <div
                                        class="list-group-item px-0 border-bottom py-2 d-flex justify-content-between align-items-center">
                                        <a href="${media.fileUrl}" target="_blank"
                                            class="text-decoration-none text-truncate d-block" style="max-width: 65%;">
                                            <i
                                                class="fa-solid ${media.type == 'VIDEO' ? 'fa-video' : 'fa-image'} text-muted me-2"></i>${media.fileUrl}
                                        </a>
                                        <span class="badge bg-light text-dark border rounded-pill px-2"
                                            style="font-size: 0.7rem;">${media.type}</span>
                                    </div>
                                </c:forEach>
                                <c:if test="${empty mediaList}">
                                    <div class="text-center text-muted py-3 small">
                                        <i class="fa-regular fa-folder-open fs-3 d-block mb-2 text-light"></i>
                                        No media uploaded.
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Customer Reviews -->
                <div class="col-md-6 mb-4">
                    <div class="card shadow-sm border-0 rounded-4 h-100">
                        <div class="card-header bg-white border-bottom-0 pt-3 pb-2">
                            <h6 class="card-title fw-bold text-secondary mb-0"><i
                                    class="fa-solid fa-star text-warning me-2"></i>Reviews & Feedback</h6>
                        </div>
                        <div class="card-body p-0">
                            <div class="list-group list-group-flush" style="max-height: 320px; overflow-y: auto;">
                                <c:forEach var="review" items="${reviews}">
                                    <div class="list-group-item p-3 border-bottom">
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <h6 class="mb-0 fw-bold text-dark"><i
                                                    class="fa-solid fa-user-circle text-muted me-1"></i>${review.customerName}
                                            </h6>
                                            <div class="text-warning small">
                                                <c:forEach begin="1" end="${review.rating}"><i
                                                        class="fa-solid fa-star"></i></c:forEach>
                                                <c:forEach begin="1" end="${5 - review.rating}"><i
                                                        class="fa-regular fa-star"></i></c:forEach>
                                            </div>
                                        </div>
                                        <p class="mb-0 small text-muted text-break">"${review.comment}"</p>
                                    </div>
                                </c:forEach>
                                <c:if test="${empty reviews}">
                                    <div class="text-center text-muted py-5">
                                        <i class="fa-regular fa-comment-dots fs-1 d-block mb-3 text-light"></i>
                                        <span class="small">No reviews available yet.</span>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../common/footer.jsp" %>