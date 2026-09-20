<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patients - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Patients</h1>
        <a class="btn" href="${pageContext.request.contextPath}/patient-register">+ Register Patient</a>
    </div>

    <form class="search-bar" action="${pageContext.request.contextPath}/patient-search" method="get">
        <input type="text" name="keyword" placeholder="Search by ID, name or phone..." value="${keyword}">
        <button type="submit" class="btn">Search</button>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/patients">Reset</a>
    </form>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr><th>#</th><th>Name</th><th>Gender</th><th>Phone</th><th>Blood Group</th><th>Registered</th><th>Actions</th></tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${patients}">
                <tr>
                    <td><c:out value="${p.patientId}"/></td>
                    <td><c:out value="${p.patientName}"/></td>
                    <td><c:out value="${p.gender}"/></td>
                    <td><c:out value="${p.phone}"/></td>
                    <td><c:out value="${p.bloodGroup}"/></td>
                    <td><fmt:formatDate value="${p.createdAt}" pattern="dd-MMM-yyyy"/></td>
                    <td>
                        <a class="btn btn-secondary btn-small" href="${pageContext.request.contextPath}/patient-view?id=${p.patientId}">View</a>
                        <a class="btn btn-secondary btn-small" href="${pageContext.request.contextPath}/patient-edit?id=${p.patientId}">Edit</a>
                        <a class="btn btn-small" href="${pageContext.request.contextPath}/appointment-book?patientId=${p.patientId}">Book</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty patients}">
                <tr><td colspan="7" class="empty-state">No patients found.</td></tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
