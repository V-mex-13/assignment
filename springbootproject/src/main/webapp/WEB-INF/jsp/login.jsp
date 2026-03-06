<%@ include file="common/header.jsp" %>

    <div class="row justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="col-md-5 col-lg-4">
            <div class="card p-4 shadow-lg border-0" style="border-radius: 20px;">
                <div class="card-header text-center bg-transparent border-0 mb-3">
                    <i class="fa-solid fa-circle-user text-primary mb-3" style="font-size: 4rem;"></i>
                    <h3 class="fw-bold mb-0">Welcome Back</h3>
                    <p class="text-muted mt-2">Login to ArtistHub</p>
                </div>

                <div class="card-body">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger shadow-sm rounded">
                            <i class="fa-solid fa-triangle-exclamation me-2"></i>Invalid email or password.
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success shadow-sm rounded">
                            <i class="fa-solid fa-circle-check me-2"></i>You have been successfully logged out.
                        </div>
                    </c:if>
                    <c:if test="${param.success != null}">
                        <div class="alert alert-success shadow-sm rounded">
                            <i class="fa-solid fa-circle-check me-2"></i>Registration successful! Please login.
                        </div>
                    </c:if>

                    <form action="/login" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                        <div class="mb-4">
                            <label for="username" class="form-label fw-semibold text-secondary"><i
                                    class="fa-regular fa-envelope me-2"></i>Email Address</label>
                            <input type="email" class="form-control form-control-lg bg-light border-0 px-3"
                                id="username" name="username" placeholder="john@example.com" required
                                style="border-radius: 10px;">
                        </div>

                        <div class="mb-4">
                            <label for="password" class="form-label fw-semibold text-secondary"><i
                                    class="fa-solid fa-lock me-2"></i>Password</label>
                            <input type="password" class="form-control form-control-lg bg-light border-0 px-3"
                                id="password" name="password" placeholder="••••••••" required
                                style="border-radius: 10px;">
                        </div>

                        <div class="d-grid gap-2 mt-5">
                            <button type="submit"
                                class="btn btn-primary btn-lg fw-bold text-white rounded-pill shadow-sm"
                                style="background: linear-gradient(45deg, #0d6efd, #0dcaf0); border: none;">
                                Sign In <i class="fa-solid fa-arrow-right-to-bracket ms-2"></i>
                            </button>
                        </div>
                    </form>

                    <div class="mt-4 text-center">
                        <span class="text-muted">Don't have an account?</span>
                        <a href="/register" class="text-decoration-none fw-bold text-primary">Register here</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="common/footer.jsp" %>