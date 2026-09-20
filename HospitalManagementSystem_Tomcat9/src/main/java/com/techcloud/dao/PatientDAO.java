package com.techcloud.dao;

import com.techcloud.model.Patient;
import com.techcloud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY patient_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Search patients by ID (if numeric), name (partial match) or phone (partial match). */
    public List<Patient> searchPatients(String keyword) {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE " +
                     "CAST(patient_id AS TEXT) = ? OR " +
                     "LOWER(patient_name) LIKE LOWER(?) OR " +
                     "phone LIKE ? " +
                     "ORDER BY patient_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, keyword);
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addPatient(Patient p) {
        String sql = "INSERT INTO patients (patient_name, gender, date_of_birth, phone, email, address, blood_group) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING patient_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getPatientName());
            ps.setString(2, p.getGender());
            ps.setDate(3, p.getDateOfBirth());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getAddress());
            ps.setString(7, p.getBloodGroup());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updatePatient(Patient p) {
        String sql = "UPDATE patients SET patient_name=?, gender=?, date_of_birth=?, phone=?, email=?, " +
                     "address=?, blood_group=? WHERE patient_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getPatientName());
            ps.setString(2, p.getGender());
            ps.setDate(3, p.getDateOfBirth());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getAddress());
            ps.setString(7, p.getBloodGroup());
            ps.setInt(8, p.getPatientId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Patient mapRow(ResultSet rs) throws SQLException {
        Patient p = new Patient();
        p.setPatientId(rs.getInt("patient_id"));
        p.setPatientName(rs.getString("patient_name"));
        p.setGender(rs.getString("gender"));
        p.setDateOfBirth(rs.getDate("date_of_birth"));
        p.setPhone(rs.getString("phone"));
        p.setEmail(rs.getString("email"));
        p.setAddress(rs.getString("address"));
        p.setBloodGroup(rs.getString("blood_group"));
        p.setCreatedAt(rs.getTimestamp("created_at"));
        return p;
    }
}
