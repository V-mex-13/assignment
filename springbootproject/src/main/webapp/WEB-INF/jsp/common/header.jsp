<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>ArtistHub</title>
                <!-- Google Fonts -->
                <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
                    rel="stylesheet">
                <!-- Font Awesome -->
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
                <!-- Bootstrap 5 CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
                <style>
                    body {
                        font-family: 'Poppins', sans-serif;
                        padding-top: 76px;
                        background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
                        min-height: 100vh;
                        display: flex;
                        flex-direction: column;
                    }

                    .navbar {
                        background: rgba(33, 37, 41, 0.95) !important;
                        backdrop-filter: blur(10px);
                        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    }

                    .container.mt-4 {
                        flex-grow: 1;
                        margin-bottom: 80px;
                    }

                    .footer {
                        position: fixed;
                        bottom: 0;
                        width: 100%;
                        height: auto;
                        background-color: #212529;
                        color: #fff;
                        padding: 15px 0;
                        text-align: center;
                    }

                    .card {
                        border: none;
                        border-radius: 15px;
                        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
                        transition: transform 0.3s ease, box-shadow 0.3s ease;
                        backdrop-filter: blur(5px);
                        background: rgba(255, 255, 255, 0.9);
                    }

                    .card:hover {
                        transform: translateY(-5px);
                        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.12);
                    }

                    .btn-primary {
                        background: linear-gradient(45deg, #0d6efd, #0dcaf0);
                        border: none;
                        transition: opacity 0.3s ease;
                    }

                    .btn-primary:hover {
                        opacity: 0.9;
                        transform: scale(1.02);
                    }
                </style>
            </head>

            <body>
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                    <div class="container">
                        <a class="navbar-brand" href="/">ArtistHub</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ms-auto">
                                <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
                                <li class="nav-item"><a class="nav-link" href="/artist-list">Find Artists</a></li>

                                <sec:authorize access="isAnonymous()">
                                    <li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/register">Register</a></li>
                                </sec:authorize>

                                <sec:authorize access="hasRole('ADMIN')">
                                    <li class="nav-item"><a class="nav-link" href="/admin/dashboard">Dashboard</a></li>
                                </sec:authorize>

                                <sec:authorize access="hasRole('ARTIST')">
                                    <li class="nav-item"><a class="nav-link" href="/artist/dashboard">Dashboard</a></li>
                                </sec:authorize>

                                <sec:authorize access="hasRole('CUSTOMER')">
                                    <li class="nav-item"><a class="nav-link" href="/customer/dashboard">Dashboard</a>
                                    </li>
                                </sec:authorize>

                                <sec:authorize access="isAuthenticated()">
                                    <li class="nav-item d-flex align-items-center me-3">
                                        <span class="text-light fw-medium"><i
                                                class="fa-regular fa-user-circle me-1"></i>Welcome,
                                            <sec:authentication property="principal.name" />
                                        </span>
                                    </li>
                                    <li class="nav-item d-flex align-items-center">
                                        <form action="/logout" method="post" class="m-0">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <button class="btn btn-outline-light btn-sm rounded-pill px-3 fw-bold"
                                                type="submit">Logout</button>
                                        </form>
                                    </li>
                                </sec:authorize>
                            </ul>
                        </div>
                    </div>
                </nav>
                <div class="container mt-4">