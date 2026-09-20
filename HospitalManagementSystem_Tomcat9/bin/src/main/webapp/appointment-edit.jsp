<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reschedule Appointment - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Reschedule Appointment</h1>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/appointment-list-servlet">Back to List</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error"><c:out value="${errorMessage}"/></div>
    </c:if>

    <div class="card">
        <p><strong>Patient:</strong> <c:out value="${appointment.patientName}"/> &nbsp;|&nbsp;
           <strong>Doctor:</strong> <c:out value="${appointment.doctorName}"/></p>

        <form action="${pageContext.request.contextPath}/appointment-edit" method="post"
              onsubmit="return validateAppointmentForm(this);">
            <input type="hidden" name="appointmentId" value="${appointment.appointmentId}">
            <input type="hidden" name="doctorId" value="${appointment.doctorId}">
            <div class="form-row">
                <div class="form-group">
                    <label for="appointmentDate">New Date</label>
                    <input type="date" id="appointmentDate" name="appointmentDate" value="${appointment.appointmentDate}" required>
                </div>
                <div class="form-group">
                    <label for="appointmentTime">New Time</label>
                    <input type="time" id="appointmentTime" name="appointmentTime" required>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Reschedule</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/appointment-list-servlet">Cancel</a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
