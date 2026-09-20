package com.techcloud.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Simple session-based authentication + role check filter.
 * Public pages (login, static assets) are allowed through; everything
 * else requires a logged-in user, and admin-only pages require role=ADMIN.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());

        boolean isPublic = path.equals("/login.jsp")
                || path.equals("/login")
                || path.startsWith("/css/")
                || path.startsWith("/js/")
                || path.startsWith("/images/")
                || path.equals("/error.jsp");

        if (isPublic) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (!loggedIn) {
            resp.sendRedirect(contextPath + "/login.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");

        // Admin-only pages
        if ((path.startsWith("/doctor-list.jsp") || path.startsWith("/doctor-add.jsp")
                || path.startsWith("/doctor-edit.jsp") || path.startsWith("/department-list.jsp")
                || path.startsWith("/department-add.jsp") || path.startsWith("/admin-dashboard.jsp"))
                && !"ADMIN".equals(role)) {
            resp.sendRedirect(contextPath + "/error.jsp");
            return;
        }

        // Doctor-only pages
        if ((path.startsWith("/doctor-dashboard.jsp") || path.startsWith("/doctor-appointments.jsp")
                || path.startsWith("/consultation.jsp") || path.startsWith("/prescription.jsp"))
                && !"DOCTOR".equals(role)) {
            resp.sendRedirect(contextPath + "/error.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
