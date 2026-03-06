<%@ include file="common/header.jsp" %>
    <div class="row justify-content-center align-items-center mb-5" style="min-height: 60vh;">
        <div class="col-md-8 col-lg-6">
            <div class="text-center mb-4 mt-3">
                <i class="fa-regular fa-comments display-4 text-primary mb-3"></i>
                <h2 class="fw-bold mb-0">We Value Your Feedback</h2>
                <p class="text-muted mt-2">Help us improve ArtistHub by sharing your thoughts.</p>
            </div>

            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show shadow-sm rounded-4 border-0" role="alert">
                    <i class="fa-solid fa-triangle-exclamation me-2"></i>${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <c:if test="${param.success != null}">
                <div class="alert alert-success alert-dismissible fade show shadow-sm rounded-4 border-0" role="alert">
                    <i class="fa-solid fa-circle-check me-2"></i>Thank you! Your feedback has been submitted
                    successfully.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <div class="card shadow-lg border-0 rounded-4 overflow-hidden">
                <div class="card-body p-5">
                    <form action="/feedback/submit" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                        <div class="mb-4">
                            <label for="message" class="form-label fw-semibold text-secondary">
                                <i class="fa-solid fa-pen text-info me-2"></i>Your Message
                            </label>
                            <textarea class="form-control bg-light border-0 shadow-sm rounded-3 p-3" id="message"
                                name="message" rows="5"
                                placeholder="Tell us what you love, what needs improvement, or any bug you discovered..."
                                required></textarea>
                            <div class="form-text mt-2 text-muted"><i class="fa-solid fa-shield-halved me-1"></i>Your
                                feedback is sent anonymously if not logged in.</div>
                        </div>

                        <div class="d-grid gap-2 mt-4">
                            <button type="submit"
                                class="btn btn-primary btn-lg fw-bold rounded-pill text-white shadow-sm"
                                style="background: linear-gradient(45deg, #0d6efd, #0dcaf0); border: none;">
                                <i class="fa-regular fa-paper-plane me-2"></i>Submit Feedback
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="common/footer.jsp" %>