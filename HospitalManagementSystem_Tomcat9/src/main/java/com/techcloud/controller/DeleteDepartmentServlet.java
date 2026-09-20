package com.techcloud.controller;

import com.techcloud.dao.DepartmentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/department-delete")
public class DeleteDepartmentServlet extends HttpServlet {

    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        departmentDAO.deleteDepartment(id); // soft delete -> INACTIVE
        resp.sendRedirect(req.getContextPath() + "/departments");
    }
}
