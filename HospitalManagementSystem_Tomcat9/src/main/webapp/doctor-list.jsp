<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctors - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Doctors</h1>
        <a class="btn" href="${pageContext.request.contextPath}/doctor-add">+ Add Doctor</a>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-success"><c:out value="${message}"/></div>
    </c:if>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr>
                <th>#</th><th>Name</th><th>Department</th><th>Specialization</th>
                <th>Phone</th><th>Fee</th><th>Status</th><th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="d" items="${doctors}">
                <tr>
                    <td><c:out value="${d.doctorId}"/></td>
                    <td><c:out value="${d.doctorName}"/></td>
                    <td><c:out value="${d.departmentName}"/></td>
                    <td><c:out value="${d.specialization}"/></td>
                    <td><c:out value="${d.phone}"/></td>
                    <td><c:out value="${d.consultationFee}"/></td>
                    <td>
                        <span class="badge badge-${d.status.toLowerCase()}"><c:out value="${d.status}"/></span>
                    </td>
                    <td>
                        <a class="btn btn-secondary btn-small" href="${pageContext.request.contextPath}/doctor-edit?id=${d.doctorId}">Edit</a>
                        <a class="btn btn-danger btn-small" href="${pageContext.request.contextPath}/doctor-delete?id=${d.doctorId}"
                           onclick="return confirm('Deactivate this doctor?');">Deactivate</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty doctors}">
                <tr><td colspan="8" class="empty-state">No doctors found.</td></tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
