<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Consultation - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Consultation</h1>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/doctor-appointments-servlet">Back</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error"><c:out value="${errorMessage}"/></div>
    </c:if>

    <div class="card">
        <p><strong>Patient:</strong> <c:out value="${appointment.patientName}"/> &nbsp;|&nbsp;
           <strong>Reason:</strong> <c:out value="${appointment.reason}"/></p>

        <form action="${pageContext.request.contextPath}/consultation" method="post"
              onsubmit="return required(this.diagnosis, 'Diagnosis');">
            <input type="hidden" name="appointmentId" value="${appointment.appointmentId}">
            <input type="hidden" name="doctorId" value="${appointment.doctorId}">
            <input type="hidden" name="patientId" value="${appointment.patientId}">

            <div class="form-group">
                <label for="symptoms">Symptoms</label>
                <textarea id="symptoms" name="symptoms" required></textarea>
            </div>
            <div class="form-group">
                <label for="diagnosis">Diagnosis</label>
                <textarea id="diagnosis" name="diagnosis" required></textarea>
            </div>
            <div class="form-group">
                <label for="doctorNotes">Doctor Notes</label>
                <textarea id="doctorNotes" name="doctorNotes"></textarea>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Save & Continue to Prescription</button>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
