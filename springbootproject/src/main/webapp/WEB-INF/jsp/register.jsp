<%@ include file="common/header.jsp" %>
    <div class="row justify-content-center align-items-center py-5"
        style="min-height: 80vh; background-color: #f8f9fa;">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header bg-white border-0 pt-4 pb-0 text-center">
                    <h3 class="fw-bold text-dark mb-1">Create an Account</h3>
                    <p class="text-secondary small">Join the ArtistHub community</p>
                </div>
                <div class="card-body px-5 pb-5 pt-3">
                    <form id="registerForm" action="/register-action" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                        <!-- Profile Picture Section -->
                        <div class="mb-4 text-center">
                            <div class="position-relative d-inline-block">
                                <img id="imagePreview"
                                    src="https://ui-avatars.com/api/?name=User&background=f1f5f9&color=334155"
                                    class="rounded-circle shadow-sm"
                                    style="width: 100px; height: 100px; object-fit: cover; border: 2px solid #e2e8f0;">
                                <label for="profileImageFile"
                                    class="position-absolute bottom-0 end-0 bg-primary text-white rounded-circle d-flex align-items-center justify-content-center cursor-pointer shadow-sm"
                                    style="width: 32px; height: 32px; cursor: pointer; transition: background-color 0.2s;">
                                    <i class="fa-solid fa-camera small"></i>
                                </label>
                                <input type="file" id="profileImageFile" name="profileImageFile" class="d-none"
                                    accept="image/png, image/jpeg" onchange="previewImage(event)">
                            </div>
                            <div class="form-text mt-2 small text-muted">Allowed: JPG, PNG (Max 2MB)</div>
                        </div>

                        <!-- Role Selection -->
                        <div class="mb-4">
                            <label class="form-label fw-medium text-dark small">I want to register as a</label>
                            <select class="form-select border-secondary-subtle py-2 cursor-pointer" id="role"
                                name="role" onchange="toggleFields()">
                                <option value="customer">Customer (Looking to hire artists)</option>
                                <option value="artist">Artist (Offering creative services)</option>
                            </select>
                        </div>

                        <!-- Basic Information -->
                        <div class="row g-3 mb-3">
                            <div class="col-sm-6">
                                <label class="form-label fw-medium text-dark small">Full Name</label>
                                <input type="text" class="form-control border-secondary-subtle py-2" name="name"
                                    placeholder="John Doe" required>
                            </div>
                            <div class="col-sm-6">
                                <label class="form-label fw-medium text-dark small">Email Address</label>
                                <input type="email" class="form-control border-secondary-subtle py-2" name="email"
                                    placeholder="name@example.com" required>
                            </div>
                        </div>

                        <div class="row g-3 mb-4">
                            <div class="col-sm-6">
                                <label class="form-label fw-medium text-dark small">Password</label>
                                <input type="password" class="form-control border-secondary-subtle py-2" name="password"
                                    placeholder="••••••••" required>
                            </div>
                            <div class="col-sm-6">
                                <label class="form-label fw-medium text-dark small">Phone Number</label>
                                <input type="tel" class="form-control border-secondary-subtle py-2" name="phone"
                                    placeholder="+1 (234) 567-8900">
                            </div>
                        </div>

                        <!-- Role-Specific Fields Container -->
                        <div class="bg-light p-4 rounded-3 mb-4 border border-1 border-light-subtle">

                            <!-- Artist Fields -->
                            <div id="artistFields" style="display: none;">
                                <h6 class="text-primary mb-3 fw-bold small text-uppercase tracking-wide">Professional
                                    Details</h6>
                                <div class="row g-3 mb-3">
                                    <div class="col-sm-6">
                                        <label class="form-label fw-medium text-dark small">Category</label>
                                        <input type="text" class="form-control border-secondary-subtle" name="category"
                                            placeholder="Musician, Painter, etc.">
                                    </div>
                                    <div class="col-sm-6">
                                        <label class="form-label fw-medium text-dark small">Experience (Years)</label>
                                        <input type="number" class="form-control border-secondary-subtle"
                                            name="experienceYears" min="0" placeholder="e.g. 5">
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label fw-medium text-dark small">Price Per Event /
                                        Session</label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-white border-secondary-subtle">$</span>
                                        <input type="number" class="form-control border-secondary-subtle"
                                            name="pricePerEvent" step="0.01" min="0" placeholder="0.00">
                                    </div>
                                </div>
                                <div>
                                    <label class="form-label fw-medium text-dark small">Professional Biography</label>
                                    <textarea class="form-control border-secondary-subtle" name="description" rows="3"
                                        placeholder="Describe your creative background and offerings..."></textarea>
                                </div>
                            </div>

                            <!-- Customer Fields -->
                            <div id="customerFields" class="mb-0">
                                <h6 class="text-primary mb-3 fw-bold small text-uppercase tracking-wide">Location
                                    Details</h6>
                                <div>
                                    <label class="form-label fw-medium text-dark small">Billing/Home Address</label>
                                    <input type="text" class="form-control border-secondary-subtle py-2" name="address"
                                        placeholder="123 Main St, City, State, ZIP">
                                </div>
                            </div>
                        </div>

                        <!-- Error Handling -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger d-flex align-items-center py-2 px-3 small rounded-3 border-0 bg-danger-subtle text-danger"
                                role="alert">
                                <i class="fa-solid fa-circle-exclamation me-2"></i>
                                <div>${error}</div>
                            </div>
                        </c:if>

                        <!-- Submit Buttons -->
                        <div class="d-grid gap-2 mt-2">
                            <button type="submit" class="btn btn-primary btn-lg fw-semibold py-2 rounded-3 shadow-sm"
                                style="transition: all 0.2s;">
                                Complete Registration
                            </button>
                        </div>

                        <div class="text-center mt-4 pt-3 border-top border-light-subtle">
                            <span class="text-secondary small">Already have an account?</span>
                            <a href="/login" class="text-primary text-decoration-none fw-semibold small">Sign in</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        function toggleFields() {
            const role = document.getElementById('role').value;
            const artistFields = document.getElementById('artistFields');
            const customerFields = document.getElementById('customerFields');
            const artistInputs = artistFields.querySelectorAll('input, select, textarea');
            const customerInputs = customerFields.querySelectorAll('input, select, textarea');

            if (role === 'artist') {
                artistFields.style.display = 'block';
                customerFields.style.display = 'none';

                // Enable artist inputs, disable customer inputs
                artistInputs.forEach(el => el.disabled = false);
                customerInputs.forEach(el => el.disabled = true);
            } else {
                artistFields.style.display = 'none';
                customerFields.style.display = 'block';

                // Enable customer inputs, disable artist inputs
                customerInputs.forEach(el => el.disabled = false);
                artistInputs.forEach(el => el.disabled = true);
            }
        }

        // Initialize fields properly on page load
        document.addEventListener("DOMContentLoaded", function () {
            toggleFields();
        });

        function previewImage(event) {
            const file = event.target.files[0];
            if (file) {
                // Check file size (2MB)
                if (file.size > 2 * 1024 * 1024) {
                    alert("File is too large. Maximum size is 2MB.");
                    event.target.value = "";
                    return;
                }
                const reader = new FileReader();
                reader.onload = function () {
                    const imgElement = document.getElementById('imagePreview');
                    imgElement.src = reader.result;
                }
                reader.readAsDataURL(file);
            }
        }
    </script>

    <%@ include file="common/footer.jsp" %>