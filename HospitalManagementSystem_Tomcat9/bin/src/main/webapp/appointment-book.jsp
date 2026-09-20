<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Appointment - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Book Appointment</h1>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/appointment-list-servlet">Back to List</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error"><c:out value="${errorMessage}"/></div>
    </c:if>

    <div class="card">
        <form action="${pageContext.request.contextPath}/appointment-book" method="post"
              onsubmit="return validateAppointmentForm(this);">
            <div class="form-row">
                <div class="form-group">
                    <label for="patientId">Patient</label>
                    <select id="patientId" name="patientId" required>
                        <option value="">-- Select Patient --</option>
                        <c:forEach var="p" items="${patients}">
                            <option value="${p.patientId}" ${selectedPatient != null && selectedPatient.patientId == p.patientId ? 'selected' : ''}>
                                <c:out value="${p.patientName}"/> (#<c:out value="${p.patientId}"/>, <c:out value="${p.phone}"/>)
                            </option>
                        </c:forEach>
                    </select>
                    <div style="margin-top:6px; font-size:0.82rem;">
                        Patient not registered yet?
                        <a href="${pageContext.request.contextPath}/patient-register">Register a new patient</a>
                    </div>
                </div>
                <div class="form-group">
                    <label for="doctorId">Doctor</label>
                    <select id="doctorId" name="doctorId" required>
                        <option value="">-- Select Doctor --</option>
                        <c:forEach var="doc" items="${doctors}">
                            <option value="${doc.doctorId}">
                                <c:out value="${doc.doctorName}"/> - <c:out value="${doc.specialization}"/> (<c:out value="${doc.departmentName}"/>)
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="appointmentDate">Appointment Date</label>
                    <input type="date" id="appointmentDate" name="appointmentDate" required>
                </div>
                <div class="form-group">
                    <label for="appointmentTime">Appointment Time</label>
                    <input type="time" id="appointmentTime" name="appointmentTime" required>
                </div>
            </div>
            <div class="form-group">
                <label for="reason">Reason for Visit</label>
                <textarea id="reason" name="reason"></textarea>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Book Appointment</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/appointment-list-servlet">Cancel</a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
