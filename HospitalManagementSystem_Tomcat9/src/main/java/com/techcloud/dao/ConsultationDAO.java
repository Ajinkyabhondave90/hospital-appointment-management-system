package com.techcloud.dao;

import com.techcloud.model.Consultation;
import com.techcloud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDAO {

    private static final String BASE_SELECT =
        "SELECT c.*, p.patient_name, d.doctor_name FROM consultations c " +
        "JOIN patients p ON c.patient_id = p.patient_id " +
        "JOIN doctors d ON c.doctor_id = d.doctor_id ";

    /** Adds a consultation and returns the generated consultation_id, or -1 on failure. */
    public int addConsultation(Consultation c) {
        String sql = "INSERT INTO consultations (appointment_id, doctor_id, patient_id, symptoms, diagnosis, doctor_notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?) RETURNING consultation_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getAppointmentId());
            ps.setInt(2, c.getDoctorId());
            ps.setInt(3, c.getPatientId());
            ps.setString(4, c.getSymptoms());
            ps.setString(5, c.getDiagnosis());
            ps.setString(6, c.getDoctorNotes());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Consultation getConsultationByAppointmentId(int appointmentId) {
        String sql = BASE_SELECT + "WHERE c.appointment_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Consultation> getConsultationsForPatient(int patientId) {
        List<Consultation> list = new ArrayList<>();
        String sql = BASE_SELECT + "WHERE c.patient_id = ? ORDER BY c.consultation_date DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Consultation mapRow(ResultSet rs) throws SQLException {
        Consultation c = new Consultation();
        c.setConsultationId(rs.getInt("consultation_id"));
        c.setAppointmentId(rs.getInt("appointment_id"));
        c.setDoctorId(rs.getInt("doctor_id"));
        c.setPatientId(rs.getInt("patient_id"));
        c.setPatientName(rs.getString("patient_name"));
        c.setDoctorName(rs.getString("doctor_name"));
        c.setSymptoms(rs.getString("symptoms"));
        c.setDiagnosis(rs.getString("diagnosis"));
        c.setDoctorNotes(rs.getString("doctor_notes"));
        c.setConsultationDate(rs.getTimestamp("consultation_date"));
        return c;
    }
}
