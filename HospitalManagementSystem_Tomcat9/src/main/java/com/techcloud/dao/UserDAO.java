package com.techcloud.dao;

import com.techcloud.model.User;
import com.techcloud.util.DBConnection;
import com.techcloud.util.DBUtil;

import java.sql.*;

public class UserDAO {

    /**
     * Validates login credentials.
     * Returns the User object if username/password/status match, otherwise null.
     */
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND status = 'ACTIVE'";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, con);
        }
        return null;
    }

    public User findById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, con);
        }
        return null;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setRole(rs.getString("role"));
        u.setStatus(rs.getString("status"));
        u.setCreatedAt(rs.getTimestamp("created_at"));
        return u;
    }
}
