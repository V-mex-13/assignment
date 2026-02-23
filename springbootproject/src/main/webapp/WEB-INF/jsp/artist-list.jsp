<%@ include file="common/header.jsp" %>

    <div class="container mt-4">
        <h2 class="text-center mb-4">Code Talented Artists</h2>

        <div class="row">
            <c:forEach var="artist" items="${artists}">
                <div class="col-md-4 mb-4">
                    <div class="card h-100 shadow-sm">
                        <!-- Profile Image -->
                        <img src="${not empty artist.profilePicUrl ? artist.profilePicUrl : 'https://via.placeholder.com/300x200?text=No+Image'}"
                            class="card-img-top" alt="${artist.name}" style="height: 200px; object-fit: cover;">
                        <div class="card-body">
                            <h5 class="card-title">${artist.name}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${artist.category}</h6>
                            <p class="card-text">${artist.description}</p>
                            <p class="card-text"><strong>Price:</strong> $${artist.pricePerEvent}</p>
                            <p class="card-text"><small class="text-muted">Experience: ${artist.experienceYears}
                                    years</small></p>
                        </div>
                        <div class="card-footer bg-white border-top-0 d-grid gap-2">
                            <sec:authorize access="hasRole('CUSTOMER')">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#bookingModal${artist.id}">
                                    Book Now
                                </button>
                            </sec:authorize>
                            <sec:authorize access="!isAuthenticated()">
                                <a href="/login" class="btn btn-outline-primary">Login to Book</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>

                <!-- Booking Modal -->
                <div class="modal fade" id="bookingModal${artist.id}" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Book ${artist.name}</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <form action="/book-artist" method="post">
                                <div class="modal-body">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <input type="hidden" name="artistId" value="${artist.id}" />
                                    <div class="mb-3">
                                        <label class="form-label">Event Date</label>
                                        <input type="date" class="form-control" name="eventDate" required>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Confirm Booking</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@ include file="common/footer.jsp" %>