package com.techcloud.controller;

import com.techcloud.dao.PatientDAO;
import com.techcloud.model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/patient-register")
public class AddPatientServlet extends HttpServlet {

    private final PatientDAO patientDAO = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/patient-register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient p = new Patient();
        p.setPatientName(req.getParameter("patientName"));
        p.setGender(req.getParameter("gender"));

        String dob = req.getParameter("dateOfBirth");
        if (dob != null && !dob.isEmpty()) {
            p.setDateOfBirth(Date.valueOf(dob));
        }
        p.setPhone(req.getParameter("phone"));
        p.setEmail(req.getParameter("email"));
        p.setAddress(req.getParameter("address"));
        p.setBloodGroup(req.getParameter("bloodGroup"));

        int newId = patientDAO.addPatient(p);

        // If a doctor/appointment booking flow is redirecting here first, send the
        // receptionist straight into the booking form with the new patient pre-selected.
        String redirectToBooking = req.getParameter("bookAfter");
        if ("true".equals(redirectToBooking) && newId > 0) {
            resp.sendRedirect(req.getContextPath() + "/appointment-book.jsp?patientId=" + newId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/patients");
        }
    }
}
