<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patient Profile - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><c:out value="${patient.patientName}"/></h1>
        <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/patient-edit?id=${patient.patientId}">Edit</a>
            <a class="btn" href="${pageContext.request.contextPath}/appointment-book?patientId=${patient.patientId}">Book Appointment</a>
        </div>
    </div>

    <div class="card">
        <div class="form-row">
            <div><strong>Patient ID:</strong> <c:out value="${patient.patientId}"/></div>
            <div><strong>Gender:</strong> <c:out value="${patient.gender}"/></div>
            <div><strong>Date of Birth:</strong> <fmt:formatDate value="${patient.dateOfBirth}" pattern="dd-MMM-yyyy"/></div>
        </div>
        <div class="form-row" style="margin-top:10px;">
            <div><strong>Phone:</strong> <c:out value="${patient.phone}"/></div>
            <div><strong>Email:</strong> <c:out value="${patient.email}"/></div>
            <div><strong>Blood Group:</strong> <c:out value="${patient.bloodGroup}"/></div>
        </div>
        <div style="margin-top:10px;"><strong>Address:</strong> <c:out value="${patient.address}"/></div>
    </div>

    <div class="card">
        <h2 style="margin-top:0;">Appointment History</h2>
        <div class="table-wrapper">
            <table>
                <thead><tr><th>Date</th><th>Time</th><th>Doctor</th><th>Reason</th><th>Status</th></tr></thead>
                <tbody>
                <c:forEach var="a" items="${appointments}">
                    <tr>
                        <td><fmt:formatDate value="${a.appointmentDate}" pattern="dd-MMM-yyyy"/></td>
                        <td><c:out value="${a.appointmentTime}"/></td>
                        <td><c:out value="${a.doctorName}"/></td>
                        <td><c:out value="${a.reason}"/></td>
                        <td><span class="badge badge-${a.status.toLowerCase()}"><c:out value="${a.status}"/></span></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty appointments}">
                    <tr><td colspan="5" class="empty-state">No appointment history yet.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <div class="card">
        <h2 style="margin-top:0;">Consultation History</h2>
        <c:forEach var="c" items="${consultations}">
            <div style="padding:12px 0; border-bottom:1px solid var(--border);">
                <fmt:formatDate value="${c.consultationDate}" pattern="dd-MMM-yyyy HH:mm"/> —
                Dr. <c:out value="${c.doctorName}"/><br>
                <strong>Symptoms:</strong> <c:out value="${c.symptoms}"/><br>
                <strong>Diagnosis:</strong> <c:out value="${c.diagnosis}"/><br>
                <strong>Notes:</strong> <c:out value="${c.doctorNotes}"/>
            </div>
        </c:forEach>
        <c:if test="${empty consultations}">
            <div class="empty-state">No consultation records yet.</div>
        </c:if>
    </div>
</div>
</body>
</html>
