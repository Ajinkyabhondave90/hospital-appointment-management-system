package com.techcloud.controller;

import com.techcloud.dao.DepartmentDAO;
import com.techcloud.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/department-add")
public class AddDepartmentServlet extends HttpServlet {

    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/department-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department d = new Department();
        d.setDepartmentName(req.getParameter("departmentName"));
        d.setDescription(req.getParameter("description"));
        d.setStatus("ACTIVE");

        departmentDAO.addDepartment(d);
        resp.sendRedirect(req.getContextPath() + "/departments");
    }
}
