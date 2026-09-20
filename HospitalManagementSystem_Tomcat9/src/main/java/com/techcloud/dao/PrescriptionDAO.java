package com.techcloud.dao;

import com.techcloud.model.Prescription;
import com.techcloud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public boolean addPrescription(Prescription p) {
        String sql = "INSERT INTO prescriptions (consultation_id, medicine_name, dosage, duration, instructions) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getConsultationId());
            ps.setString(2, p.getMedicineName());
            ps.setString(3, p.getDosage());
            ps.setString(4, p.getDuration());
            ps.setString(5, p.getInstructions());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Prescription> getPrescriptionsByConsultationId(int consultationId) {
        List<Prescription> list = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions WHERE consultation_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, consultationId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Prescription mapRow(ResultSet rs) throws SQLException {
        Prescription p = new Prescription();
        p.setPrescriptionId(rs.getInt("prescription_id"));
        p.setConsultationId(rs.getInt("consultation_id"));
        p.setMedicineName(rs.getString("medicine_name"));
        p.setDosage(rs.getString("dosage"));
        p.setDuration(rs.getString("duration"));
        p.setInstructions(rs.getString("instructions"));
        return p;
    }
}
