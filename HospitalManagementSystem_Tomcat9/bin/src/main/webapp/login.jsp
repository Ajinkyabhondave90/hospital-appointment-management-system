<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-wrapper">
    <div class="login-card">
        <h1>&#127973; Hospital Management System</h1>
        <p class="subtitle">Sign in to continue</p>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error"><c:out value="${errorMessage}"/></div>
        </c:if>
        <c:if test="${param.loggedOut == 'true'}">
            <div class="alert alert-success">You have been logged out.</div>
        </c:if>

        <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post"
              onsubmit="return validateLoginForm(this);">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>

        <div class="login-hint">
            <strong>Demo accounts (sample_data.sql):</strong><br>
            Admin: admin / admin123<br>
            Receptionist: reception1 / recep123<br>
            Doctor: dr.sharma / doctor123
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
