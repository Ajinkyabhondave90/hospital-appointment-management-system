package com.techcloud.controller;

import com.techcloud.dao.DoctorDAO;
import com.techcloud.model.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {

    private final DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        req.setAttribute("doctors", doctors);
        req.getRequestDispatcher("/doctor-list.jsp").forward(req, resp);
    }
}
