<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <% java.util.List<String> userList = java.util.Arrays.asList("Alice", "Bob", "Charlie");
            request.setAttribute("users", userList);
            %>
            <html>

            <head>
                <title>JSTL Loop Demo</title>
            </head>

            <body>
                <h2>Application Information (Implicit Objects)</h2>
                <p>Session ID: ${pageContext.session.id}</p>
                <p>Context Path: ${pageContext.request.contextPath}</p>

                <h2>Active Users List (JSTL)</h2>
                <ul>
                    <c:forEach var="u" items="${users}">
                        <li>User Name: ${u}</li>
                    </c:forEach>
                </ul>
            </body>

            </html>