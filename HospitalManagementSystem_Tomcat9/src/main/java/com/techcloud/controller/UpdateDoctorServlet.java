package com.techcloud.controller;

import com.techcloud.dao.DepartmentDAO;
import com.techcloud.dao.DoctorDAO;
import com.techcloud.model.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/doctor-edit")
public class UpdateDoctorServlet extends HttpServlet {

    private final DoctorDAO doctorDAO = new DoctorDAO();
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("doctor", doctorDAO.getDoctorById(id));
        req.setAttribute("departments", departmentDAO.getAllDepartments());
        req.getRequestDispatcher("/doctor-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Doctor d = new Doctor();
        d.setDoctorId(Integer.parseInt(req.getParameter("doctorId")));
        d.setDoctorName(req.getParameter("doctorName"));
        d.setEmail(req.getParameter("email"));
        d.setPhone(req.getParameter("phone"));
        d.setSpecialization(req.getParameter("specialization"));
        d.setDepartmentId(Integer.parseInt(req.getParameter("departmentId")));
        d.setExperience(Integer.parseInt(req.getParameter("experience")));
        d.setConsultationFee(new BigDecimal(req.getParameter("consultationFee")));
        d.setStatus(req.getParameter("status"));

        doctorDAO.updateDoctor(d);
        resp.sendRedirect(req.getContextPath() + "/doctors");
    }
}
