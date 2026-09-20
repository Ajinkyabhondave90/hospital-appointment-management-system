package com.techcloud.dao;

import com.techcloud.model.Appointment;
import com.techcloud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    private static final String BASE_SELECT =
        "SELECT a.*, p.patient_name, d.doctor_name FROM appointments a " +
        "JOIN patients p ON a.patient_id = p.patient_id " +
        "JOIN doctors d ON a.doctor_id = d.doctor_id ";

    public boolean bookAppointment(Appointment appt) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, reason, status) " +
                     "VALUES (?, ?, ?, ?, ?, 'BOOKED')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, appt.getPatientId());
            ps.setInt(2, appt.getDoctorId());
            ps.setDate(3, appt.getAppointmentDate());
            ps.setTime(4, appt.getAppointmentTime());
            ps.setString(5, appt.getReason());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Appointment getAppointmentById(int id) {
        String sql = BASE_SELECT + "WHERE a.appointment_id = ?";
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

    /**
     * Filters appointments by optional date / doctorId / status, with basic
     * pagination using LIMIT and OFFSET.
     */
    public List<Appointment> filterAppointments(Date date, Integer doctorId, String status, int limit, int offset) {
        List<Appointment> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(BASE_SELECT + "WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (date != null) {
            sql.append("AND a.appointment_date = ? ");
            params.add(date);
        }
        if (doctorId != null) {
            sql.append("AND a.doctor_id = ? ");
            params.add(doctorId);
        }
        if (status != null && !status.isEmpty()) {
            sql.append("AND a.status = ? ");
            params.add(status);
        }
        sql.append("ORDER BY a.appointment_date DESC, a.appointment_time DESC LIMIT ? OFFSET ?");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int idx = 1;
            for (Object param : params) {
                ps.setObject(idx++, param);
            }
            ps.setInt(idx++, limit);
            ps.setInt(idx, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countAppointments(Date date, Integer doctorId, String status) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM appointments a WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        if (date != null) { sql.append("AND a.appointment_date = ? "); params.add(date); }
        if (doctorId != null) { sql.append("AND a.doctor_id = ? "); params.add(doctorId); }
        if (status != null && !status.isEmpty()) { sql.append("AND a.status = ? "); params.add(status); }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int idx = 1;
            for (Object param : params) ps.setObject(idx++, param);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Appointment> getTodaysAppointmentsForDoctor(int doctorId) {
        List<Appointment> list = new ArrayList<>();
        String sql = BASE_SELECT + "WHERE a.doctor_id = ? AND a.appointment_date = CURRENT_DATE " +
                     "ORDER BY a.appointment_time";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Appointment> getTodaysAppointmentsAll() {
        List<Appointment> list = new ArrayList<>();
        String sql = BASE_SELECT + "WHERE a.appointment_date = CURRENT_DATE ORDER BY a.appointment_time";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStatus(int appointmentId, String status) {
        String sql = "UPDATE appointments SET status = ? WHERE appointment_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, appointmentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rescheduleAppointment(int appointmentId, Date newDate, Time newTime) {
        String sql = "UPDATE appointments SET appointment_date = ?, appointment_time = ?, status = 'RESCHEDULED' " +
                     "WHERE appointment_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, newDate);
            ps.setTime(2, newTime);
            ps.setInt(3, appointmentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Appointment> getConsultationHistoryForPatient(int patientId) {
        List<Appointment> list = new ArrayList<>();
        String sql = BASE_SELECT + "WHERE a.patient_id = ? ORDER BY a.appointment_date DESC, a.appointment_time DESC";
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

    /** Simple report: count of appointments grouped by status. */
    public java.util.Map<String, Integer> getAppointmentCountsByStatus() {
        java.util.Map<String, Integer> map = new java.util.LinkedHashMap<>();
        String sql = "SELECT status, COUNT(*) AS cnt FROM appointments GROUP BY status ORDER BY status";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("status"), rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    private Appointment mapRow(ResultSet rs) throws SQLException {
        Appointment a = new Appointment();
        a.setAppointmentId(rs.getInt("appointment_id"));
        a.setPatientId(rs.getInt("patient_id"));
        a.setPatientName(rs.getString("patient_name"));
        a.setDoctorId(rs.getInt("doctor_id"));
        a.setDoctorName(rs.getString("doctor_name"));
        a.setAppointmentDate(rs.getDate("appointment_date"));
        a.setAppointmentTime(rs.getTime("appointment_time"));
        a.setReason(rs.getString("reason"));
        a.setStatus(rs.getString("status"));
        a.setCreatedAt(rs.getTimestamp("created_at"));
        return a;
    }
}
