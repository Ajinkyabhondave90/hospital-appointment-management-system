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

@WebServlet("/patient-edit")
public class UpdatePatientServlet extends HttpServlet {

    private final PatientDAO patientDAO = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("patient", patientDAO.getPatientById(id));
        req.getRequestDispatcher("/patient-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient p = new Patient();
        p.setPatientId(Integer.parseInt(req.getParameter("patientId")));
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

        patientDAO.updatePatient(p);
        resp.sendRedirect(req.getContextPath() + "/patient-view?id=" + p.getPatientId());
    }
}
