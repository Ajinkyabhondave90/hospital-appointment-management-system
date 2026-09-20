package com.techcloud.controller;

import com.techcloud.dao.DoctorDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/doctor-delete")
public class DeleteDoctorServlet extends HttpServlet {

    private final DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        doctorDAO.deleteDoctor(id); // soft delete -> sets status to INACTIVE
        resp.sendRedirect(req.getContextPath() + "/doctors");
    }
}
