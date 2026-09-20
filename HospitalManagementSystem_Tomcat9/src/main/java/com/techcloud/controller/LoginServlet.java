package com.techcloud.controller;

import com.techcloud.dao.DoctorDAO;
import com.techcloud.dao.UserDAO;
import com.techcloud.model.Doctor;
import com.techcloud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDAO.login(username, password);

        if (user == null) {
            req.setAttribute("errorMessage", "Invalid username or password.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        String contextPath = req.getContextPath();

        switch (user.getRole()) {
            case "ADMIN":
                resp.sendRedirect(contextPath + "/reports");
                break;
            case "RECEPTIONIST":
                resp.sendRedirect(contextPath + "/appointment-list-servlet");
                break;
            case "DOCTOR":
                Doctor doctor = doctorDAO.getDoctorByUserId(user.getUserId());
                if (doctor != null) {
                    session.setAttribute("doctor", doctor);
                    session.setAttribute("doctorId", doctor.getDoctorId());
                }
                resp.sendRedirect(contextPath + "/doctor-dashboard.jsp");
                break;
            default:
                resp.sendRedirect(contextPath + "/login.jsp");
        }
    }
}
