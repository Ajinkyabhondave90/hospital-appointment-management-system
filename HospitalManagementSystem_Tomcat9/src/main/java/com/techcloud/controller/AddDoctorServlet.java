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

@WebServlet("/doctor-add")
public class AddDoctorServlet extends HttpServlet {

    private final DoctorDAO doctorDAO = new DoctorDAO();
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("departments", departmentDAO.getAllDepartments());
        req.getRequestDispatcher("/doctor-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Doctor d = new Doctor();
            d.setDoctorName(req.getParameter("doctorName"));
            d.setEmail(req.getParameter("email"));
            d.setPhone(req.getParameter("phone"));
            d.setSpecialization(req.getParameter("specialization"));
            d.setDepartmentId(Integer.parseInt(req.getParameter("departmentId")));
            d.setExperience(Integer.parseInt(req.getParameter("experience")));
            d.setConsultationFee(new BigDecimal(req.getParameter("consultationFee")));
            d.setStatus("ACTIVE");

            boolean success = doctorDAO.addDoctor(d);
            req.setAttribute("message", success ? "Doctor added successfully." : "Failed to add doctor.");
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Please enter valid numeric values for department, experience and fee.");
        }
        resp.sendRedirect(req.getContextPath() + "/doctors");
    }
}
