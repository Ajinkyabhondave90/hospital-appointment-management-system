package com.techcloud.controller;

import com.techcloud.dao.AppointmentDAO;
import com.techcloud.dao.DoctorDAO;
import com.techcloud.dao.PatientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reports")
public class ReportsServlet extends HttpServlet {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();
    private final PatientDAO patientDAO = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("statusCounts", appointmentDAO.getAppointmentCountsByStatus());
        req.setAttribute("totalDoctors", doctorDAO.getAllDoctors().size());
        req.setAttribute("totalPatients", patientDAO.getAllPatients().size());
        req.setAttribute("todaysAppointments", appointmentDAO.getTodaysAppointmentsAll());
        req.getRequestDispatcher("/admin-dashboard.jsp").forward(req, resp);
    }
}
