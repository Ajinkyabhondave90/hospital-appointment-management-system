<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Prescription - Hospital Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Prescription</h1>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/doctor-appointments-servlet">Back</a>
    </div>

    <div class="card">
        <form action="${pageContext.request.contextPath}/prescription" method="post">
            <input type="hidden" name="consultationId" value="${consultationId}">
            <input type="hidden" name="appointmentId" value="${appointmentId}">

            <label style="font-weight:600; font-size:0.88rem;">Medicines</label>
            <div id="medicineRows" style="margin-top:8px;">
                <div class="med-row">
                    <input type="text" name="medicineName" placeholder="Medicine name">
                    <input type="text" name="dosage" placeholder="Dosage (e.g. 1 tablet)">
                    <input type="text" name="duration" placeholder="Duration (e.g. 5 days)">
                    <input type="text" name="instructions" placeholder="Instructions">
                    <span></span>
                </div>
            </div>
            <button type="button" class="btn btn-secondary btn-small" onclick="addMedicineRow()">+ Add Medicine</button>

            <div class="form-actions">
                <button type="submit" class="btn btn-success">Save Prescription & Complete Appointment</button>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
