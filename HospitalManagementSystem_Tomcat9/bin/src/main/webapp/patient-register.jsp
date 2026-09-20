<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Patient - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Register Patient</h1>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/patients">Back to List</a>
    </div>

    <div class="card">
        <form action="${pageContext.request.contextPath}/patient-register" method="post"
              onsubmit="return validatePatientForm(this);">
            <div class="form-row">
                <div class="form-group">
                    <label for="patientName">Patient Name</label>
                    <input type="text" id="patientName" name="patientName" required>
                </div>
                <div class="form-group">
                    <label for="gender">Gender</label>
                    <select id="gender" name="gender" required>
                        <option value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                        <option value="OTHER">Other</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="dateOfBirth">Date of Birth</label>
                    <input type="date" id="dateOfBirth" name="dateOfBirth">
                </div>
                <div class="form-group">
                    <label for="bloodGroup">Blood Group</label>
                    <input type="text" id="bloodGroup" name="bloodGroup" placeholder="e.g. O+">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" id="phone" name="phone" maxlength="10" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email">
                </div>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <textarea id="address" name="address"></textarea>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Register Patient</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/patients">Cancel</a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
