<%@ include file="common/header.jsp" %>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="mb-4 text-center">Submit Feedback</h2>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <div class="card shadow-sm border-0">
                <div class="card-body p-4">
                    <form action="/feedback/submit" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                        <div class="mb-3">
                            <label for="message" class="form-label">Message</label>
                            <textarea class="form-control" id="message" name="message" rows="4" required></textarea>
                            <div class="form-text">We value your feedback to improve ArtistHub.</div>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Submit Feedback</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="common/footer.jsp" %>