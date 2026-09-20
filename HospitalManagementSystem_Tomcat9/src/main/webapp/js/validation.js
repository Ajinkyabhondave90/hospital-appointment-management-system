/* ============================================================
   Hospital Appointment & Patient Management System
   Basic client-side (frontend) form validation
   ============================================================ */

function showError(inputEl, message) {
    let errorEl = inputEl.parentElement.querySelector(".field-error");
    if (!errorEl) {
        errorEl = document.createElement("div");
        errorEl.className = "field-error";
        inputEl.parentElement.appendChild(errorEl);
    }
    errorEl.textContent = message;
    errorEl.style.display = "block";
    inputEl.style.borderColor = "#dc2626";
}

function clearError(inputEl) {
    const errorEl = inputEl.parentElement.querySelector(".field-error");
    if (errorEl) errorEl.style.display = "none";
    inputEl.style.borderColor = "";
}

function required(inputEl, label) {
    if (!inputEl.value || inputEl.value.trim() === "") {
        showError(inputEl, label + " is required.");
        return false;
    }
    clearError(inputEl);
    return true;
}

function validPhone(inputEl) {
    const pattern = /^[0-9]{10}$/;
    if (!pattern.test(inputEl.value.trim())) {
        showError(inputEl, "Enter a valid 10-digit phone number.");
        return false;
    }
    clearError(inputEl);
    return true;
}

function validEmail(inputEl) {
    if (inputEl.value.trim() === "") return true; // email optional in some forms
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!pattern.test(inputEl.value.trim())) {
        showError(inputEl, "Enter a valid email address.");
        return false;
    }
    clearError(inputEl);
    return true;
}

function notPastDate(inputEl) {
    const selected = new Date(inputEl.value);
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    if (selected < today) {
        showError(inputEl, "Date cannot be in the past.");
        return false;
    }
    clearError(inputEl);
    return true;
}

/** Generic patient form validator used on patient-register.jsp / patient-edit.jsp */
function validatePatientForm(form) {
    let valid = true;
    if (!required(form.patientName, "Patient name")) valid = false;
    if (!required(form.phone, "Phone number")) valid = false;
    else if (!validPhone(form.phone)) valid = false;
    if (form.email && !validEmail(form.email)) valid = false;
    return valid;
}

/** Validator used on appointment-book.jsp / appointment-edit.jsp */
function validateAppointmentForm(form) {
    let valid = true;
    if (form.patientId && !required(form.patientId, "Patient")) valid = false;
    if (!required(form.doctorId, "Doctor")) valid = false;
    if (!required(form.appointmentDate, "Appointment date")) valid = false;
    else if (!notPastDate(form.appointmentDate)) valid = false;
    if (!required(form.appointmentTime, "Appointment time")) valid = false;
    return valid;
}

/** Validator used on doctor-add.jsp / doctor-edit.jsp */
function validateDoctorForm(form) {
    let valid = true;
    if (!required(form.doctorName, "Doctor name")) valid = false;
    if (!required(form.phone, "Phone number")) valid = false;
    else if (!validPhone(form.phone)) valid = false;
    if (form.email && !validEmail(form.email)) valid = false;
    if (!required(form.departmentId, "Department")) valid = false;
    return valid;
}

/** Validator used on login.jsp */
function validateLoginForm(form) {
    let valid = true;
    if (!required(form.username, "Username")) valid = false;
    if (!required(form.password, "Password")) valid = false;
    return valid;
}

/** Adds another blank medicine row on the prescription form */
function addMedicineRow() {
    const container = document.getElementById("medicineRows");
    if (!container) return;
    const row = document.createElement("div");
    row.className = "med-row";
    row.innerHTML =
        '<input type="text" name="medicineName" placeholder="Medicine name">' +
        '<input type="text" name="dosage" placeholder="Dosage (e.g. 1 tablet)">' +
        '<input type="text" name="duration" placeholder="Duration (e.g. 5 days)">' +
        '<input type="text" name="instructions" placeholder="Instructions">' +
        '<button type="button" class="btn btn-secondary btn-small" onclick="this.parentElement.remove()">Remove</button>';
    container.appendChild(row);
}
