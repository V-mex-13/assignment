<%@ include file="../common/header.jsp" %>
    <div class="row">
        <div class="col-md-12">
            <h2 class="mb-4">Platform Feedback</h2>

            <div class="mb-3">
                <a href="/admin/dashboard" class="btn btn-outline-secondary">&larr; Back to Dashboard</a>
            </div>

            <div class="card shadow-sm border-0">
                <div class="card-body p-0">
                    <table class="table table-striped mb-0">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>User</th>
                                <th>Message</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="feedback" items="${feedbacks}">
                                <tr>
                                    <td>${feedback.id}</td>
                                    <td>${feedback.userName}</td>
                                    <td>${feedback.message}</td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty feedbacks}">
                                <tr>
                                    <td colspan="3" class="text-center text-muted py-3">No feedback available.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../common/footer.jsp" %>