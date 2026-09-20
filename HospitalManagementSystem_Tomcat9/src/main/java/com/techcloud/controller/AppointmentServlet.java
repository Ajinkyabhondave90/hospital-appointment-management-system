package com.techcloud.controller;

import com.techcloud.dao.AppointmentDAO;
import com.techcloud.dao.DoctorDAO;
import com.techcloud.model.Appointment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/appointment-list-servlet")
public class AppointmentServlet extends HttpServlet {

    private static final int PAGE_SIZE = 10;

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateParam = req.getParameter("date");
        String doctorParam = req.getParameter("doctorId");
        String status = req.getParameter("status");
        String pageParam = req.getParameter("page");

        Date date = (dateParam != null && !dateParam.isEmpty()) ? Date.valueOf(dateParam) : null;
        Integer doctorId = (doctorParam != null && !doctorParam.isEmpty()) ? Integer.parseInt(doctorParam) : null;

        int page = 1;
        if (pageParam != null && !pageParam.isEmpty()) {
            try { page = Math.max(1, Integer.parseInt(pageParam)); } catch (NumberFormatException ignored) { }
        }
        int offset = (page - 1) * PAGE_SIZE;

        List<Appointment> appointments = appointmentDAO.filterAppointments(date, doctorId, status, PAGE_SIZE, offset);
        int totalCount = appointmentDAO.countAppointments(date, doctorId, status);
        int totalPages = (int) Math.ceil(totalCount / (double) PAGE_SIZE);

        req.setAttribute("appointments", appointments);
        req.setAttribute("doctors", doctorDAO.getActiveDoctors());
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", Math.max(totalPages, 1));
        req.setAttribute("filterDate", dateParam);
        req.setAttribute("filterDoctorId", doctorParam);
        req.setAttribute("filterStatus", status);

        req.getRequestDispatcher("/appointment-list.jsp").forward(req, resp);
    }
}
