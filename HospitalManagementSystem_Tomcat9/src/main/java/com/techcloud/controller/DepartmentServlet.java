package com.techcloud.controller;

import com.techcloud.dao.DepartmentDAO;
import com.techcloud.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("departments", departmentDAO.getAllDepartments());
        req.getRequestDispatcher("/department-list.jsp").forward(req, resp);
    }
}
