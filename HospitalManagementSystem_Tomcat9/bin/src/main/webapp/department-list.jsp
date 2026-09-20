<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Departments - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Departments</h1>
        <a class="btn" href="${pageContext.request.contextPath}/department-add">+ Add Department</a>
    </div>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr><th>#</th><th>Name</th><th>Description</th><th>Status</th><th>Actions</th></tr>
            </thead>
            <tbody>
            <c:forEach var="dep" items="${departments}">
                <tr>
                    <td><c:out value="${dep.departmentId}"/></td>
                    <td><c:out value="${dep.departmentName}"/></td>
                    <td><c:out value="${dep.description}"/></td>
                    <td><span class="badge badge-${dep.status.toLowerCase()}"><c:out value="${dep.status}"/></span></td>
                    <td>
                        <a class="btn btn-danger btn-small" href="${pageContext.request.contextPath}/department-delete?id=${dep.departmentId}"
                           onclick="return confirm('Deactivate this department?');">Deactivate</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty departments}">
                <tr><td colspan="5" class="empty-state">No departments found.</td></tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
