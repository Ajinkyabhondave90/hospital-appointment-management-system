package com.techcloud.controller;

import com.techcloud.dao.AppointmentDAO;
import com.techcloud.dao.DoctorDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/appointment-edit")
public class RescheduleAppointmentServlet extends HttpServlet {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("appointment", appointmentDAO.getAppointmentById(id));
        req.getRequestDispatcher("/appointment-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
        int doctorId = Integer.parseInt(req.getParameter("doctorId"));
        Date newDate = Date.valueOf(req.getParameter("appointmentDate"));
        Time newTime = Time.valueOf(req.getParameter("appointmentTime") + ":00");

        if (!doctorDAO.isDoctorAvailable(doctorId, newDate, newTime)) {
            req.setAttribute("errorMessage", "Doctor is not available at the selected date & time.");
            req.setAttribute("appointment", appointmentDAO.getAppointmentById(appointmentId));
            req.getRequestDispatcher("/appointment-edit.jsp").forward(req, resp);
            return;
        }

        appointmentDAO.rescheduleAppointment(appointmentId, newDate, newTime);
        resp.sendRedirect(req.getContextPath() + "/appointment-list-servlet");
    }
}
