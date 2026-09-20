<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctor Dashboard - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Welcome, Dr. <c:out value="${sessionScope.doctor.doctorName}"/></h1>
    </div>

    <div class="dashboard-cards">
        <div class="stat-card">
            <div class="stat-number"><c:out value="${sessionScope.doctor.specialization}"/></div>
            <div class="stat-label">Specialization</div>
        </div>
        <div class="stat-card">
            <div class="stat-number"><c:out value="${sessionScope.doctor.departmentName}"/></div>
            <div class="stat-label">Department</div>
        </div>
        <div class="stat-card">
            <div class="stat-number"><c:out value="${sessionScope.doctor.experience}"/> yrs</div>
            <div class="stat-label">Experience</div>
        </div>
    </div>

    <div class="card">
        <h2 style="margin-top:0;">Quick Actions</h2>
        <a class="btn" href="${pageContext.request.contextPath}/doctor-appointments-servlet">View Today's Appointments</a>
    </div>
</div>
</body>
</html>
