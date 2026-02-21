# Java Web Technologies - Module 3 Lab Exercises

**Instructor Checklist & Project Layout overview:**

```text
web assignmant/
│
├── pom.xml                               <-- Maven configuration & dependencies (JSTL, Servlets)
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── lab/
        │           ├── filters/
        │           │   └── ValidationFilter.java         <-- Task 3.2 (Server-Side Validation)
        │           │
        │           └── servlets/
        │               ├── RequestHeaderServlet.java     <-- Task 2.1 (HTTP Headers)
        │               ├── LifecycleServlet.java         <-- Task 2.2 (Servlet Lifecycle)
        │               ├── ConfigContextServlet.java     <-- Task 2.3 (ServletConfig/Context)
        │               ├── CgiStyleServlet.java          <-- Task 2.4 (CGI vs HttpServlet)
        │               ├── LoginDispatcherServlet.java   <-- Task 3.1 (RequestDispatcher)
        │               ├── ContextSetterServlet.java     <-- Task 3.3 (Shared ServletContext)
        │               └── SessionLoginServlet.java      <-- Task 4.2 (HttpSession & Cookies)
        │
        └── webapp/
            ├── index.html                                <-- Task 1.1 (Master HTML Page with Forms)
            ├── css-methods.html                          <-- Task 1.2 (Inline, Internal, External CSS)
            ├── box-model.html                            <-- Task 1.3 (Box Model & Selectors)
            ├── pseudo-classes.html                       <-- Task 1.4 (Hover & Focus Interactivity)
            ├── users.jsp                                 <-- Task 4.1 (JSTL & Implicit Objects)
            ├── dashboard.jsp                             <-- Task 4.2 (Session Dashboard)
            │
            ├── styles/
            │   └── style.css                             <-- Task 1.2 (External Stylesheet)
            │
            └── WEB-INF/
                └── web.xml                               <-- Deployment Descriptor (Mappings & Configs)
```
