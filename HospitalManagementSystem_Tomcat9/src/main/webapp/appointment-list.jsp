<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Appointments - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Appointments</h1>
        <a class="btn" href="${pageContext.request.contextPath}/appointment-book">+ Book Appointment</a>
    </div>

    <div class="card">
        <form action="${pageContext.request.contextPath}/appointment-list-servlet" method="get" class="form-row">
            <div class="form-group">
                <label for="date">Date</label>
                <input type="date" id="date" name="date" value="${filterDate}">
            </div>
            <div class="form-group">
                <label for="doctorId">Doctor</label>
                <select id="doctorId" name="doctorId">
                    <option value="">All Doctors</option>
                    <c:forEach var="doc" items="${doctors}">
                        <option value="${doc.doctorId}" ${filterDoctorId == doc.doctorId ? 'selected' : ''}>
                            <c:out value="${doc.doctorName}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <select id="status" name="status">
                    <option value="">All</option>
                    <option value="BOOKED" ${filterStatus == 'BOOKED' ? 'selected' : ''}>Booked</option>
                    <option value="COMPLETED" ${filterStatus == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                    <option value="CANCELLED" ${filterStatus == 'CANCELLED' ? 'selected' : ''}>Cancelled</option>
                    <option value="RESCHEDULED" ${filterStatus == 'RESCHEDULED' ? 'selected' : ''}>Rescheduled</option>
                </select>
            </div>
            <div class="form-group" style="align-self:end;">
                <button type="submit" class="btn">Filter</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/appointment-list-servlet">Reset</a>
            </div>
        </form>
    </div>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr><th>#</th><th>Patient</th><th>Doctor</th><th>Date</th><th>Time</th><th>Reason</th><th>Status</th><th>Actions</th></tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${appointments}">
                <tr>
                    <td><c:out value="${a.appointmentId}"/></td>
                    <td><c:out value="${a.patientName}"/></td>
                    <td><c:out value="${a.doctorName}"/></td>
                    <td><fmt:formatDate value="${a.appointmentDate}" pattern="dd-MMM-yyyy"/></td>
                    <td><c:out value="${a.appointmentTime}"/></td>
                    <td><c:out value="${a.reason}"/></td>
                    <td><span class="badge badge-${a.status.toLowerCase()}"><c:out value="${a.status}"/></span></td>
                    <td>
                        <c:if test="${a.status == 'BOOKED' || a.status == 'RESCHEDULED'}">
                            <a class="btn btn-secondary btn-small" href="${pageContext.request.contextPath}/appointment-edit?id=${a.appointmentId}">Reschedule</a>
                            <a class="btn btn-danger btn-small" href="${pageContext.request.contextPath}/appointment-cancel?id=${a.appointmentId}"
                               onclick="return confirm('Cancel this appointment?');">Cancel</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty appointments}">
                <tr><td colspan="8" class="empty-state">No appointments found for the selected filters.</td></tr>
            </c:if>
            </tbody>
        </table>
    </div>

    <div class="pagination">
        <c:forEach begin="1" end="${totalPages}" var="p">
            <a class="${p == currentPage ? 'active' : ''}"
               href="${pageContext.request.contextPath}/appointment-list-servlet?page=${p}&date=${filterDate}&doctorId=${filterDoctorId}&status=${filterStatus}">
                <c:out value="${p}"/>
            </a>
        </c:forEach>
    </div>
</div>
</body>
</html>
