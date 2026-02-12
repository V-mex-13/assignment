<%@ include file="common/header.jsp" %>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-header text-center">
                    <h3>Register</h3>
                </div>
                <div class="card-body">
                    <!-- NOTE: For simplicity, this form submits to a hypothetical MVC endpoint or uses JS to call the API.
                     Given the tasks, we should stick to MVC form submission OR use JS to hit the REST API.
                     Since we have AuthRestController, let's use JS to submit to /api/auth/signup for better separation 
                     OR implement a Form Controller. The plan said "AuthRestController". 
                     So I will use JavaScript to submit this form to the API. -->

                    <form id="registerForm" action="/register-action" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="mb-3">
                            <label class="form-label">I am a:</label>
                            <select class="form-select" id="role" name="role" onchange="toggleFields()">
                                <option value="customer">Customer</option>
                                <option value="artist">Artist</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input type="text" class="form-control" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Phone</label>
                            <input type="text" class="form-control" name="phone">
                        </div>

                        <!-- Artist Fields -->
                        <div id="artistFields" style="display: none;">
                            <div class="mb-3">
                                <label class="form-label">Category</label>
                                <input type="text" class="form-control" name="category">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Experience (Years)</label>
                                <input type="number" class="form-control" name="experienceYears">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Price Per Event</label>
                                <input type="number" class="form-control" name="pricePerEvent">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Description</label>
                                <textarea class="form-control" name="description" rows="3"></textarea>
                            </div>
                        </div>

                        <!-- Customer Fields -->
                        <div id="customerFields">
                            <div class="mb-3">
                                <label class="form-label">Address</label>
                                <input type="text" class="form-control" name="address">
                            </div>
                        </div>

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                Error:
                                <c:out value="${error}" />
                            </div>
                        </c:if>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        function toggleFields() {
            const role = document.getElementById('role').value;
            if (role === 'artist') {
                document.getElementById('artistFields').style.display = 'block';
                document.getElementById('customerFields').style.display = 'none';
            } else {
                document.getElementById('artistFields').style.display = 'none';
                document.getElementById('customerFields').style.display = 'block';
            }
        }
    </script>

    <%@ include file="common/footer.jsp" %>