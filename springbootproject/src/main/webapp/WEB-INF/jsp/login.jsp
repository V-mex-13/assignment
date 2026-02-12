<%@ include file="common/header.jsp" %>

    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card mt-5">
                <div class="card-header text-center">
                    <h3>Login</h3>
                </div>
                <div class="card-body">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            Invalid username or password.
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            You have been logged out.
                        </div>
                    </c:if>

                    <form action="/login" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="mb-3">
                            <label for="username" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Sign In</button>
                        </div>
                    </form>
                    <div class="mt-3 text-center">
                        <a href="/register">Don't have an account? Register</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="common/footer.jsp" %>