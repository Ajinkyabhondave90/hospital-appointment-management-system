package com.techcloud.controller;

import com.techcloud.dao.PatientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

    private final PatientDAO patientDAO = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("patients", patientDAO.getAllPatients());
        req.getRequestDispatcher("/patient-list.jsp").forward(req, resp);
    }
}
