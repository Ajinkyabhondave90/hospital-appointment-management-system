package com.techcloud.controller;

import com.techcloud.dao.AppointmentDAO;
import com.techcloud.dao.ConsultationDAO;
import com.techcloud.model.Appointment;
import com.techcloud.model.Consultation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/consultation")
public class ConsultationServlet extends HttpServlet {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final ConsultationDAO consultationDAO = new ConsultationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
        Appointment appt = appointmentDAO.getAppointmentById(appointmentId);
        req.setAttribute("appointment", appt);
        req.getRequestDispatcher("/consultation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
        int doctorId = Integer.parseInt(req.getParameter("doctorId"));
        int patientId = Integer.parseInt(req.getParameter("patientId"));

        Consultation c = new Consultation();
        c.setAppointmentId(appointmentId);
        c.setDoctorId(doctorId);
        c.setPatientId(patientId);
        c.setSymptoms(req.getParameter("symptoms"));
        c.setDiagnosis(req.getParameter("diagnosis"));
        c.setDoctorNotes(req.getParameter("doctorNotes"));

        int consultationId = consultationDAO.addConsultation(c);

        if (consultationId > 0) {
            // Move straight to the prescription form for this consultation
            resp.sendRedirect(req.getContextPath() + "/prescription?consultationId=" + consultationId
                    + "&appointmentId=" + appointmentId);
        } else {
            req.setAttribute("errorMessage", "Failed to save consultation. Please try again.");
            req.setAttribute("appointment", appointmentDAO.getAppointmentById(appointmentId));
            req.getRequestDispatcher("/consultation.jsp").forward(req, resp);
        }
    }
}
