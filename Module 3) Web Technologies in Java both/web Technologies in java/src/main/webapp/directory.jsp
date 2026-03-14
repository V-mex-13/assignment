<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Master URL Directory - Java Web Tech Lab</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; }
        .container { background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); max-width: 800px; margin: auto; }
        h1 { color: #333; }
        h2 { color: #0056b3; border-bottom: 2px solid #0056b3; padding-bottom: 5px; }
        ul { list-style-type: none; padding: 0; }
        li { margin: 10px 0; background: #eef; padding: 10px; border-radius: 4px; }
        a { text-decoration: none; color: #004494; font-weight: bold; font-size: 16px; display: block; }
        a:hover { color: #ff6600; }
        .desc { font-size: 14px; color: #555; display: block; margin-top: 5px; font-weight: normal; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Master URL Directory</h1>
        <p>Use these links to test every feature of the Module 3 Java Web Tech Assignment safely.</p>
        
        <h2>1. Main Frontend Pages (JSP Views)</h2>
        <ul>
            <li><a href="index.jsp">🏠 Main Navigation & Login (index.jsp)<span class="desc">The primary landing page containing the user registration/login form.</span></a></li>
            <li><a href="css-methods.jsp">🎨 CSS Methods Demo (css-methods.jsp)<span class="desc">Demonstrates Inline, Internal, and External CSS styling.</span></a></li>
            <li><a href="box-model.jsp">📦 CSS Box Model Demo (box-model.jsp)<span class="desc">Visual demonstration of margins, borders, padding, and content.</span></a></li>
            <li><a href="pseudo-classes.jsp">✨ CSS Pseudo-Classes Demo (pseudo-classes.jsp)<span class="desc">Interactive states using hover and focus selectors without JS.</span></a></li>
            <li><a href="users.jsp">👥 User List Demo (users.jsp)<span class="desc">Uses Jakarta Standard Tag Library (JSTL) to iterate over an implicit user list.</span></a></li>
            <li><a href="dashboard.jsp">🔒 Secure Dashboard (dashboard.jsp)<span class="desc">Displays user session and cookies (Requires Login first!).</span></a></li>
        </ul>

        <h2>2. Backend Servlet Endpoints (Controllers)</h2>
        <ul>
            <li><a href="headers">📡 Read HTTP Headers (/headers)<span class="desc">Reads your browser's raw incoming HTTP headers and prints them dynamically.</span></a></li>
            <li><a href="lifecycle">⚙️ Servlet Lifecycle (/lifecycle)<span class="desc">Triggers the init/service/destroy lifecycle methods (Check Eclipse Console for output).</span></a></li>
            <li><a href="config">🔧 Config & Context Parameters (/config)<span class="desc">Reads admin emails from the web.xml ServletConfig and ServletContext context-params.</span></a></li>
            <li><a href="setContext">💾 Set Shared Context (/setContext)<span class="desc">Injects a shared temporary variable into the server's global context.</span></a></li>
        </ul>
    </div>
</body>
</html>
