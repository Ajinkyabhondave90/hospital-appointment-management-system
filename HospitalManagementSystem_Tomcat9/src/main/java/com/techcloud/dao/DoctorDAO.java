package com.techcloud.dao;

import com.techcloud.model.Doctor;
import com.techcloud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    private static final String BASE_SELECT =
        "SELECT d.*, dep.department_name FROM doctors d " +
        "LEFT JOIN departments dep ON d.department_id = dep.department_id ";

    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = BASE_SELECT + "ORDER BY d.doctor_name";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Doctor> getActiveDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = BASE_SELECT + "WHERE d.status = 'ACTIVE' ORDER BY d.doctor_name";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Doctor getDoctorById(int id) {
        String sql = BASE_SELECT + "WHERE d.doctor_id = ?";
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

    /** Used by LoginServlet to map a logged-in DOCTOR user to their doctor profile. */
    public Doctor getDoctorByUserId(int userId) {
        String sql = BASE_SELECT + "WHERE d.user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addDoctor(Doctor doc) {
        String sql = "INSERT INTO doctors (doctor_name, email, phone, specialization, department_id, " +
                     "experience, consultation_fee, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc.getDoctorName());
            ps.setString(2, doc.getEmail());
            ps.setString(3, doc.getPhone());
            ps.setString(4, doc.getSpecialization());
            ps.setInt(5, doc.getDepartmentId());
            ps.setInt(6, doc.getExperience());
            ps.setBigDecimal(7, doc.getConsultationFee());
            ps.setString(8, doc.getStatus() == null ? "ACTIVE" : doc.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDoctor(Doctor doc) {
        String sql = "UPDATE doctors SET doctor_name=?, email=?, phone=?, specialization=?, department_id=?, " +
                     "experience=?, consultation_fee=?, status=? WHERE doctor_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc.getDoctorName());
            ps.setString(2, doc.getEmail());
            ps.setString(3, doc.getPhone());
            ps.setString(4, doc.getSpecialization());
            ps.setInt(5, doc.getDepartmentId());
            ps.setInt(6, doc.getExperience());
            ps.setBigDecimal(7, doc.getConsultationFee());
            ps.setString(8, doc.getStatus());
            ps.setInt(9, doc.getDoctorId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDoctor(int id) {
        // Soft delete: keep history (appointments/consultations) intact
        String sql = "UPDATE doctors SET status = 'INACTIVE' WHERE doctor_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Checks whether a doctor already has an appointment at the given date+time. */
    public boolean isDoctorAvailable(int doctorId, Date date, Time time) {
        String sql = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ? " +
                     "AND appointment_time = ? AND status IN ('BOOKED','RESCHEDULED')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setDate(2, date);
            ps.setTime(3, time);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Doctor mapRow(ResultSet rs) throws SQLException {
        Doctor d = new Doctor();
        d.setDoctorId(rs.getInt("doctor_id"));
        d.setDoctorName(rs.getString("doctor_name"));
        d.setEmail(rs.getString("email"));
        d.setPhone(rs.getString("phone"));
        d.setSpecialization(rs.getString("specialization"));
        d.setDepartmentId(rs.getInt("department_id"));
        d.setDepartmentName(rs.getString("department_name"));
        d.setExperience(rs.getInt("experience"));
        d.setConsultationFee(rs.getBigDecimal("consultation_fee"));
        d.setStatus(rs.getString("status"));
        int uid = rs.getInt("user_id");
        d.setUserId(rs.wasNull() ? null : uid);
        return d;
    }
}
