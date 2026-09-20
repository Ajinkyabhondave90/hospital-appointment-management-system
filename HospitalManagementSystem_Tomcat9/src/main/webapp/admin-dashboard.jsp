<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Admin Dashboard</h1>
    </div>

    <div class="dashboard-cards">
        <div class="stat-card">
            <div class="stat-number"><c:out value="${totalDoctors}" default="-"/></div>
            <div class="stat-label">Total Doctors</div>
        </div>
        <div class="stat-card">
            <div class="stat-number"><c:out value="${totalPatients}" default="-"/></div>
            <div class="stat-label">Total Patients</div>
        </div>
        <c:forEach var="entry" items="${statusCounts}">
            <div class="stat-card">
                <div class="stat-number"><c:out value="${entry.value}"/></div>
                <div class="stat-label"><c:out value="${entry.key}"/> Appointments</div>
            </div>
        </c:forEach>
    </div>

    <div class="card">
        <h2 style="margin-top:0;">Today's Appointments</h2>
        <div class="table-wrapper">
            <table>
                <thead>
                <tr>
                    <th>#</th><th>Patient</th><th>Doctor</th><th>Time</th><th>Reason</th><th>Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${todaysAppointments}">
                    <tr>
                        <td><c:out value="${a.appointmentId}"/></td>
                        <td><c:out value="${a.patientName}"/></td>
                        <td><c:out value="${a.doctorName}"/></td>
                        <td><c:out value="${a.appointmentTime}"/></td>
                        <td><c:out value="${a.reason}"/></td>
                        <td><span class="badge badge-${a.status.toLowerCase()}"><c:out value="${a.status}"/></span></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty todaysAppointments}">
                    <tr><td colspan="6" class="empty-state">No appointments scheduled for today.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <div class="card">
        <h2 style="margin-top:0;">Quick Links</h2>
        <a class="btn" href="${pageContext.request.contextPath}/doctor-add">+ Add Doctor</a>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/department-add">+ Add Department</a>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/appointment-list-servlet">View All Appointments</a>
    </div>
</div>
</body>
</html>
