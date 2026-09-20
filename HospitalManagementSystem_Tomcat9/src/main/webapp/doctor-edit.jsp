<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Doctor - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Edit Doctor</h1>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/doctors">Back to List</a>
    </div>

    <div class="card">
        <form action="${pageContext.request.contextPath}/doctor-edit" method="post"
              onsubmit="return validateDoctorForm(this);">
            <input type="hidden" name="doctorId" value="${doctor.doctorId}">
            <div class="form-row">
                <div class="form-group">
                    <label for="doctorName">Doctor Name</label>
                    <input type="text" id="doctorName" name="doctorName" value="${doctor.doctorName}" required>
                </div>
                <div class="form-group">
                    <label for="specialization">Specialization</label>
                    <input type="text" id="specialization" name="specialization" value="${doctor.specialization}" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${doctor.email}">
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" id="phone" name="phone" value="${doctor.phone}" maxlength="10" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="departmentId">Department</label>
                    <select id="departmentId" name="departmentId" required>
                        <c:forEach var="dept" items="${departments}">
                            <option value="${dept.departmentId}" ${dept.departmentId == doctor.departmentId ? 'selected' : ''}>
                                <c:out value="${dept.departmentName}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="experience">Experience (years)</label>
                    <input type="number" id="experience" name="experience" value="${doctor.experience}" min="0" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="consultationFee">Consultation Fee</label>
                    <input type="number" id="consultationFee" name="consultationFee" value="${doctor.consultationFee}" min="0" step="0.01" required>
                </div>
                <div class="form-group">
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option value="ACTIVE" ${doctor.status == 'ACTIVE' ? 'selected' : ''}>ACTIVE</option>
                        <option value="INACTIVE" ${doctor.status == 'INACTIVE' ? 'selected' : ''}>INACTIVE</option>
                    </select>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Update Doctor</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/doctors">Cancel</a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
