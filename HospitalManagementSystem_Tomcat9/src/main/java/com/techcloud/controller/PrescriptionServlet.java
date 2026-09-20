package com.techcloud.controller;

import com.techcloud.dao.AppointmentDAO;
import com.techcloud.dao.PrescriptionDAO;
import com.techcloud.model.Prescription;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/prescription")
public class PrescriptionServlet extends HttpServlet {

    private final PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("consultationId", req.getParameter("consultationId"));
        req.setAttribute("appointmentId", req.getParameter("appointmentId"));
        req.getRequestDispatcher("/prescription.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int consultationId = Integer.parseInt(req.getParameter("consultationId"));
        int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));

        // Multiple medicine rows can be submitted as parallel arrays: medicineName[], dosage[], duration[], instructions[]
        String[] medicineNames = req.getParameterValues("medicineName");
        String[] dosages = req.getParameterValues("dosage");
        String[] durations = req.getParameterValues("duration");
        String[] instructions = req.getParameterValues("instructions");

        if (medicineNames != null) {
            for (int i = 0; i < medicineNames.length; i++) {
                if (medicineNames[i] == null || medicineNames[i].trim().isEmpty()) continue;
                Prescription p = new Prescription();
                p.setConsultationId(consultationId);
                p.setMedicineName(medicineNames[i]);
                p.setDosage(dosages != null && i < dosages.length ? dosages[i] : "");
                p.setDuration(durations != null && i < durations.length ? durations[i] : "");
                p.setInstructions(instructions != null && i < instructions.length ? instructions[i] : "");
                prescriptionDAO.addPrescription(p);
            }
        }

        // Mark the appointment as completed once prescription step is done
        appointmentDAO.updateStatus(appointmentId, "COMPLETED");

        resp.sendRedirect(req.getContextPath() + "/doctor-appointments-servlet");
    }
}
