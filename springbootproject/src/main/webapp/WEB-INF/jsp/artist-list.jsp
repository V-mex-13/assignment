<%@ include file="common/header.jsp" %>
    <div class="row mb-5 text-center mt-3">
        <div class="col-md-12">
            <h2 class="fw-bold mb-3 display-5"><i class="fa-solid fa-users-viewfinder text-primary me-3"></i>Discover
                Talented Artists</h2>
            <p class="text-muted lead">Find the perfect artist for your next big event.</p>
        </div>
    </div>

    <div class="row g-4">
        <c:forEach var="artist" items="${artists}">
            <div class="col-lg-4 col-md-6">
                <div class="card h-100 shadow-sm border-0 rounded-4 overflow-hidden position-relative hover-zoom">
                    <!-- Profile Image -->
                    <div class="position-relative">
                        <c:choose>
                            <c:when test="${not empty artist.profilePicUrl}">
                                <img src="${artist.profilePicUrl}" class="card-img-top" alt="${artist.name}"
                                    style="height: 250px; object-fit: cover;">
                            </c:when>
                            <c:when
                                test="${not empty artist.profileImage and artist.profileImage ne 'default-avatar.png'}">
                                <img src="/uploads/${artist.profileImage}" class="card-img-top" alt="${artist.name}"
                                    style="height: 250px; object-fit: cover;">
                            </c:when>
                            <c:otherwise>
                                <img src="https://ui-avatars.com/api/?name=${artist.name}&background=random&size=250"
                                    class="card-img-top" alt="${artist.name}" style="height: 250px; object-fit: cover;">
                            </c:otherwise>
                        </c:choose>
                        <div class="position-absolute bottom-0 start-0 w-100 p-3 bg-dark bg-opacity-75 text-white"
                            style="backdrop-filter: blur(5px);">
                            <h5 class="card-title fw-bold mb-0">${artist.name}</h5>
                            <p class="mb-0 small text-uppercase fw-semibold text-info">${artist.category != null ?
                                artist.category : 'Artist'}</p>
                        </div>
                    </div>

                    <div class="card-body p-4 bg-white d-flex flex-column">
                        <div style="height: 4.5rem; overflow: hidden;" class="mb-4">
                            <p class="card-text text-muted"
                                style="display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden;">
                                <c:choose>
                                    <c:when test="${not empty artist.description}">${artist.description}</c:when>
                                    <c:otherwise>Explore my artistic portfolio and experience tailored for your event!
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>

                        <div class="d-flex justify-content-between align-items-center mb-0 mt-auto">
                            <div class="text-center p-2 bg-light rounded-3 flex-fill me-2">
                                <span class="d-block text-muted small text-uppercase">Experience</span>
                                <span class="fw-bold text-dark"><i
                                        class="fa-solid fa-briefcase text-secondary me-1"></i>${artist.experienceYears
                                    != null ? artist.experienceYears : '0'}
                                    yrs</span>
                            </div>
                            <div class="text-center p-2 bg-light rounded-3 flex-fill ms-2">
                                <span class="d-block text-muted small text-uppercase">Price</span>
                                <span class="fw-bold text-success"><i
                                        class="fa-solid fa-tag text-success me-1"></i>$${artist.pricePerEvent != null ?
                                    artist.pricePerEvent : '0.00'}</span>
                            </div>
                        </div>
                    </div>

                    <div class="card-footer bg-white border-top-0 p-3 pt-0 d-grid gap-2">
                        <sec:authorize access="hasRole('CUSTOMER')">
                            <button type="button" class="btn btn-primary fw-bold rounded-pill" data-bs-toggle="modal"
                                data-bs-target="#bookingModal${artist.id}">
                                <i class="fa-regular fa-calendar-check me-2"></i>Book Now
                            </button>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <a href="/login" class="btn btn-outline-primary fw-bold rounded-pill">
                                <i class="fa-solid fa-lock me-2"></i>Login to Book
                            </a>
                        </sec:authorize>
                    </div>
                </div>
            </div>

            <!-- Booking Modal -->
            <sec:authorize access="hasRole('CUSTOMER')">
                <div class="modal fade" id="bookingModal${artist.id}" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content border-0 rounded-4 shadow-lg">
                            <div class="modal-header bg-light border-bottom-0 pb-0">
                                <h5 class="modal-title fw-bold"><i
                                        class="fa-regular fa-calendar-plus text-primary me-2"></i>Book ${artist.name}
                                </h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <form action="/book-artist" method="post">
                                <div class="modal-body pt-4">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <input type="hidden" name="artistId" value="${artist.id}" />

                                    <div class="d-flex align-items-center mb-4 bg-light p-3 rounded-3">
                                        <h2 class="mb-0 text-success me-3">$${artist.pricePerEvent != null ?
                                            artist.pricePerEvent : '0.00'}</h2>
                                        <span class="text-muted small">Estimated cost per event.</span>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label fw-semibold text-secondary">Select Event Date</label>
                                        <div class="input-group">
                                            <span class="input-group-text bg-white border-end-0"><i
                                                    class="fa-regular fa-calendar"></i></span>
                                            <input type="date" class="form-control border-start-0 ps-0" name="eventDate"
                                                required min="<%= java.time.LocalDate.now() %>">
                                        </div>
                                        <div class="form-text mt-2">The artist will need to confirm this recording.
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer border-top-0 pt-0">
                                    <button type="button" class="btn btn-light rounded-pill px-4"
                                        data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary rounded-pill px-4 fw-bold">Confirm
                                        Request</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </sec:authorize>
        </c:forEach>
        <c:if test="${empty artists}">
            <div class="col-12 text-center py-5">
                <i class="fa-solid fa-users-slash display-1 text-muted mb-3 opacity-25"></i>
                <h4 class="text-muted">No artists available at the moment.</h4>
                <p class="text-muted">Please check back later.</p>
            </div>
        </c:if>
    </div>

    <style>
        .hover-zoom {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .hover-zoom:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1) !important;
        }

        .hover-zoom .card-img-top {
            transition: transform 0.5s ease;
        }

        .hover-zoom:hover .card-img-top {
            transform: scale(1.05);
        }
    </style>

    <%@ include file="common/footer.jsp" %>