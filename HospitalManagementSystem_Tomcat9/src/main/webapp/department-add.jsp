<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Department - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Add Department</h1>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/departments">Back to List</a>
    </div>

    <div class="card">
        <form action="${pageContext.request.contextPath}/department-add" method="post"
              onsubmit="return required(this.departmentName, 'Department name');">
            <div class="form-group">
                <label for="departmentName">Department Name</label>
                <input type="text" id="departmentName" name="departmentName" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description"></textarea>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Save Department</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/departments">Cancel</a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
