<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<div class="navbar">
    <div class="brand">&#127973; Hospital Management System</div>
    <div style="display:flex; align-items:center;">
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/reports">Dashboard</a>
                <a href="${pageContext.request.contextPath}/doctors">Doctors</a>
                <a href="${pageContext.request.contextPath}/departments">Departments</a>
                <a href="${pageContext.request.contextPath}/patients">Patients</a>
                <a href="${pageContext.request.contextPath}/appointment-list-servlet">Appointments</a>
            </div>
        </c:if>
        <c:if test="${sessionScope.role == 'RECEPTIONIST'}">
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/appointment-list-servlet">Appointments</a>
                <a href="${pageContext.request.contextPath}/appointment-book">Book Appointment</a>
                <a href="${pageContext.request.contextPath}/patients">Patients</a>
                <a href="${pageContext.request.contextPath}/patient-register">Register Patient</a>
            </div>
        </c:if>
        <c:if test="${sessionScope.role == 'DOCTOR'}">
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/doctor-dashboard.jsp">Dashboard</a>
                <a href="${pageContext.request.contextPath}/doctor-appointments-servlet">Today's Appointments</a>
            </div>
        </c:if>
        <span class="user-info">
            <c:out value="${sessionScope.username}"/> (<c:out value="${sessionScope.role}"/>)
        </span>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary btn-small">Logout</a>
    </div>
</div>
