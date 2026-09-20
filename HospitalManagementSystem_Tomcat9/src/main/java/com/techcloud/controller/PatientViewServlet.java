package com.techcloud.controller;

import com.techcloud.dao.AppointmentDAO;
import com.techcloud.dao.ConsultationDAO;
import com.techcloud.dao.PatientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/patient-view")
public class PatientViewServlet extends HttpServlet {

    private final PatientDAO patientDAO = new PatientDAO();
    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final ConsultationDAO consultationDAO = new ConsultationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("patient", patientDAO.getPatientById(id));
        req.setAttribute("appointments", appointmentDAO.getConsultationHistoryForPatient(id));
        req.setAttribute("consultations", consultationDAO.getConsultationsForPatient(id));
        req.getRequestDispatcher("/patient-view.jsp").forward(req, resp);
    }
}
