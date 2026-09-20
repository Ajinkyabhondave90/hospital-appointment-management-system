<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Patient - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Edit Patient</h1>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/patient-view?id=${patient.patientId}">Back to Profile</a>
    </div>

    <div class="card">
        <form action="${pageContext.request.contextPath}/patient-edit" method="post"
              onsubmit="return validatePatientForm(this);">
            <input type="hidden" name="patientId" value="${patient.patientId}">
            <div class="form-row">
                <div class="form-group">
                    <label for="patientName">Patient Name</label>
                    <input type="text" id="patientName" name="patientName" value="${patient.patientName}" required>
                </div>
                <div class="form-group">
                    <label for="gender">Gender</label>
                    <select id="gender" name="gender" required>
                        <option value="MALE" ${patient.gender == 'MALE' ? 'selected' : ''}>Male</option>
                        <option value="FEMALE" ${patient.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
                        <option value="OTHER" ${patient.gender == 'OTHER' ? 'selected' : ''}>Other</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="dateOfBirth">Date of Birth</label>
                    <input type="date" id="dateOfBirth" name="dateOfBirth" value="${patient.dateOfBirth}">
                </div>
                <div class="form-group">
                    <label for="bloodGroup">Blood Group</label>
                    <input type="text" id="bloodGroup" name="bloodGroup" value="${patient.bloodGroup}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" id="phone" name="phone" value="${patient.phone}" maxlength="10" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${patient.email}">
                </div>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <textarea id="address" name="address">${patient.address}</textarea>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Update Patient</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/patient-view?id=${patient.patientId}">Cancel</a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
