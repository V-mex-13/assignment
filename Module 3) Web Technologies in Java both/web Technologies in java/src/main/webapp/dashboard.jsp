<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>

    <body>
        <h2>Welcome to the standard Dashboard, ${sessionScope.loggedInUser}!</h2>

        <h3>Your Cookies:</h3>
        <ul>
            <% jakarta.servlet.http.Cookie[] cookies=request.getCookies(); if (cookies !=null) { for
                (jakarta.servlet.http.Cookie c : cookies) { out.println("<li>" + c.getName() + " : " + c.getValue() + "
                </li>");
                }
                }
                %>
        </ul>
    </body>

    </html>