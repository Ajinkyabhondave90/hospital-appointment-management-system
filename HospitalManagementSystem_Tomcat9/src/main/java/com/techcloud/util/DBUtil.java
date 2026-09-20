package com.techcloud.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Small helper to close JDBC resources quietly, avoiding repeated
 * try/catch boilerplate inside every DAO method.
 */
public class DBUtil {

    private DBUtil() {
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection con) {
        try { if (rs != null) rs.close(); } catch (Exception ignored) { }
        try { if (ps != null) ps.close(); } catch (Exception ignored) { }
        try { if (con != null) con.close(); } catch (Exception ignored) { }
    }

    public static void close(PreparedStatement ps, Connection con) {
        close(null, ps, con);
    }
}
