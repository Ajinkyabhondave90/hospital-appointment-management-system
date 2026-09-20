<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Today's Appointments - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Today's Appointments</h1>
    </div>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr><th>#</th><th>Time</th><th>Patient</th><th>Reason</th><th>Status</th><th>Actions</th></tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${appointments}">
                <tr>
                    <td><c:out value="${a.appointmentId}"/></td>
                    <td><c:out value="${a.appointmentTime}"/></td>
                    <td><c:out value="${a.patientName}"/></td>
                    <td><c:out value="${a.reason}"/></td>
                    <td><span class="badge badge-${a.status.toLowerCase()}"><c:out value="${a.status}"/></span></td>
                    <td>
                        <c:if test="${a.status == 'BOOKED' || a.status == 'RESCHEDULED'}">
                            <a class="btn btn-small" href="${pageContext.request.contextPath}/consultation?appointmentId=${a.appointmentId}">Start Consultation</a>
                        </c:if>
                        <c:if test="${a.status == 'COMPLETED'}">
                            <span style="color:var(--muted); font-size:0.85rem;">Completed</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty appointments}">
                <tr><td colspan="6" class="empty-state">No appointments scheduled for today.</td></tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
