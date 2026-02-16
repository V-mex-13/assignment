<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="header">
        <h2>${param.title}</h2>
        <div class="user-info">
            Welcome, ${sessionScope.user != null ? sessionScope.user.fullName : 'Guest'}
            <span class="role-badge"
                style="font-size: 0.8rem; background: #eee; padding: 2px 5px; border-radius: 4px; margin-left: 5px;">
                ${sessionScope.user != null ? sessionScope.user.role : ''}
            </span>
        </div>
    </div>