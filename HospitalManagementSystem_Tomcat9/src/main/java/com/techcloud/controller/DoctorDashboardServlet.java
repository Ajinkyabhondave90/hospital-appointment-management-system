package com.techcloud.controller;

import com.techcloud.dao.AppointmentDAO;
import com.techcloud.model.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/doctor-appointments-servlet")
public class DoctorDashboardServlet extends HttpServlet {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Doctor doctor = (session != null) ? (Doctor) session.getAttribute("doctor") : null;

        if (doctor == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        req.setAttribute("appointments", appointmentDAO.getTodaysAppointmentsForDoctor(doctor.getDoctorId()));
        req.getRequestDispatcher("/doctor-appointments.jsp").forward(req, resp);
    }
}
