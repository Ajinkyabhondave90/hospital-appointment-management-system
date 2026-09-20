package com.techcloud.controller;

import com.techcloud.dao.AppointmentDAO;
import com.techcloud.dao.DoctorDAO;
import com.techcloud.dao.PatientDAO;
import com.techcloud.model.Appointment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/appointment-book")
public class BookAppointmentServlet extends HttpServlet {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();
    private final PatientDAO patientDAO = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("doctors", doctorDAO.getActiveDoctors());
        req.setAttribute("patients", patientDAO.getAllPatients());

        String patientId = req.getParameter("patientId");
        if (patientId != null && !patientId.isEmpty()) {
            req.setAttribute("selectedPatient", patientDAO.getPatientById(Integer.parseInt(patientId)));
        }
        req.getRequestDispatcher("/appointment-book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int patientId = Integer.parseInt(req.getParameter("patientId"));
        int doctorId = Integer.parseInt(req.getParameter("doctorId"));
        Date date = Date.valueOf(req.getParameter("appointmentDate"));
        Time time = Time.valueOf(req.getParameter("appointmentTime") + ":00");
        String reason = req.getParameter("reason");

        // Doctor availability check (Business Feature #4 in the guide)
        if (!doctorDAO.isDoctorAvailable(doctorId, date, time)) {
            req.setAttribute("errorMessage", "Selected doctor already has an appointment at this date & time. Please choose another slot.");
            req.setAttribute("doctors", doctorDAO.getActiveDoctors());
            req.setAttribute("patients", patientDAO.getAllPatients());
            req.setAttribute("selectedPatient", patientDAO.getPatientById(patientId));
            req.getRequestDispatcher("/appointment-book.jsp").forward(req, resp);
            return;
        }

        Appointment appt = new Appointment();
        appt.setPatientId(patientId);
        appt.setDoctorId(doctorId);
        appt.setAppointmentDate(date);
        appt.setAppointmentTime(time);
        appt.setReason(reason);

        appointmentDAO.bookAppointment(appt);
        resp.sendRedirect(req.getContextPath() + "/appointment-list-servlet");
    }
}
