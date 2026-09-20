<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-wrapper">
    <div class="login-card" style="text-align:center;">
        <h1>&#9888; Something went wrong</h1>
        <p class="subtitle">The page you requested is unavailable, or you don't have permission to view it.</p>
        <a class="btn" href="${pageContext.request.contextPath}/login.jsp">Back to Login</a>
    </div>
</div>
</body>
</html>
