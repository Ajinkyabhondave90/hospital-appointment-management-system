package com.techcloud.dao;

import com.techcloud.model.Department;
import com.techcloud.util.DBConnection;
import com.techcloud.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments ORDER BY department_name";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Department getDepartmentById(int id) {
        String sql = "SELECT * FROM departments WHERE department_id = ?";
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

    public boolean addDepartment(Department d) {
        String sql = "INSERT INTO departments (department_name, description, status) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getDepartmentName());
            ps.setString(2, d.getDescription());
            ps.setString(3, d.getStatus() == null ? "ACTIVE" : d.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDepartment(Department d) {
        String sql = "UPDATE departments SET department_name = ?, description = ?, status = ? WHERE department_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getDepartmentName());
            ps.setString(2, d.getDescription());
            ps.setString(3, d.getStatus());
            ps.setInt(4, d.getDepartmentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDepartment(int id) {
        // Soft delete keeps referential integrity with doctors table intact
        String sql = "UPDATE departments SET status = 'INACTIVE' WHERE department_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Department mapRow(ResultSet rs) throws SQLException {
        Department d = new Department();
        d.setDepartmentId(rs.getInt("department_id"));
        d.setDepartmentName(rs.getString("department_name"));
        d.setDescription(rs.getString("description"));
        d.setStatus(rs.getString("status"));
        return d;
    }
}
