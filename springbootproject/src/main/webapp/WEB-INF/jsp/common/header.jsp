<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ArtistHub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding-top: 70px; }
        .footer { position: fixed; bottom: 0; width: 100%; height: 60px; background-color: #f5f5f5; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">ArtistHub</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
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
                    <li class="nav-item"><a class="nav-link" href="/customer/dashboard">Dashboard</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <form action="/logout" method="post" class="d-flex">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class="btn btn-outline-light btn-sm" type="submit">Logout</button>
                        </form>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-4">
